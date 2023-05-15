import TestBasis.TestFlow;
import org.hamcrest.MatcherAssert;
import page_objects.RegistrationPage;
import utility.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import io.restassured.response.ValidatableResponse;
import static org.junit.Assert.*;
import static utility.Constants.*;
import static org.apache.groovy.parser.antlr4.util.StringUtils.isEmpty;

public class RegistrationPageTest extends TestFlow {

    private WebDriver driver;
    private String email;
    private String password;
    private String passwordValid;
    private String passwordInvalid;
    private String name;
    private UserAPI userAPI;
    private UserLogin userLogin;
    private String accessToken;

    @Before
    @Description("Инициализация данных")
    public void setUp() {
        email = randomEmail;
        passwordValid = randomCorrectPassword;
        passwordInvalid = randomIncorrectPassword;
        name = randomName;
        userAPI = new UserAPI();
        driver = WebDriverSetup.runDriver();
        driver.get(REGISTRATION_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка формы регистрации пользователя и переадресации на форму авторизации")
    @Description("После валидной регистрации, пользователь должен перенаправляться на форму авторизации")
    public void registrationFormFillingTest() {
        password = passwordValid;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFormFilling(email, password, name);
        assertEquals("Не осуществлена переадресация на страницу авторизации", LOGIN_PAGE_URL, driver.getCurrentUrl());

    }

    @Test
    @DisplayName("При попытке регистрации с паролем меньше шести символов происходит ошибка")
    @Description("Проверка ошибки при попытке ввести невалидный пароль ")
    public void isRegistrationWithIncorrectPasswordShowsErrorTest() {
        password = passwordInvalid;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationFormFilling(email, password, name);
        registrationPage.isIncorrectPasswordErrorDisplayed();
        assertEquals("URL Страницы Регистрации не совпадает с URL в документации", REGISTRATION_PAGE_URL, driver.getCurrentUrl());
        assertTrue("Ошибка о невалидном пароле менее 6 символов не отображается", registrationPage.isIncorrectPasswordErrorDisplayed());
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {
        userLogin = new UserLogin(email, password);
        ValidatableResponse userLoginRequest = userAPI.loginUser(userLogin);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        if (!isEmpty(accessToken)) {userAPI.deleteUser(accessToken);}
        WebDriverSetup.stopDriver();
    }

}
