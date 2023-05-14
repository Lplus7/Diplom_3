import TestBasis.TestFlow;
import page_objects.RegistrationPage;
import utility.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import io.restassured.response.ValidatableResponse;
import static org.apache.groovy.parser.antlr4.util.StringUtils.isEmpty;


public class RegistrationPageTest extends TestFlow {

    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    private UserAPI userAPI;
    private UserLogin userLogin;
    private String accessToken;

    @Before
    @Description("Инициализация данных")
    public void setUp() {
        email = randomEmail;
        password = randomCorrectPassword;
        name = randomName;
        userAPI = new UserAPI();
        driver = WebDriverSetup.runDriver();
        driver.get(Constants.REGISTRATION_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка формы регистрации пользователя и переадресации на форму авторизации")
    @Description("После валидной регистрации, пользователь должен перенаправляться на форму авторизации")
    public void registrationFormFillingTest() {
        password = randomCorrectPassword;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFormFilling(email, password, name);

        //удаление пользователя
        userLogin = new UserLogin(email, password);
        ValidatableResponse userLoginRequest = userAPI.loginUser(userLogin);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        userAPI.deleteUser(accessToken);
    }

    @Test
    @DisplayName("При попытке регистрации с паролем меньше шести символов происходит ошибка")
    @Description("Проверка ошибки при попытке ввести невалидный пароль ")
    public void isRegistrationWithIncorrectPasswordFailsTest() {
        password = randomIncorrectPassword;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFormFilling(email, password, name);
        registrationPage.isIncorrectPasswordErrorDisplayed();
        userLogin = new UserLogin(email, password);
        ValidatableResponse userLoginRequest = userAPI.loginUser(userLogin);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        if (!isEmpty(accessToken)) {
            userAPI.deleteUser(accessToken);
        }
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {

        WebDriverSetup.stopDriver();
    }

}
