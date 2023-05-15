import TestBasis.TestFlow;
import utility.*;
import page_objects.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.restassured.response.ValidatableResponse;
import java.time.Duration;
import static utility.Constants.*;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetPageTest extends TestFlow {
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
        userLogin = new UserLogin(email, password, name);
        driver = WebDriverSetup.runDriver();
        ValidatableResponse userLoginRequest = userAPI.createUser(userLogin);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        driver.get(LOGIN_PAGE_URL);
        new LoginPage(driver).login(email, password);
    }

    @Test
    @DisplayName("Проверяю выход по кнопке Выйти из Личного кабинета")
    @Description("После выхода из аккаунта, пользователь должен быть перенаправлен на страницу авторизации")
    public void exitButtonClickTest() {
        new MainPage(driver).headerLoginButtonClick();
        new PersonalCabinetPage(driver).logoutButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals("URL Страницы Авторизации не совпадает с URL в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяю переход по кнопке Конструктор из Личного Кабинета")
    @Description("Проверка перехода авторизованного пользователя из ЛК по кнопке Конструктор и переадресации на главную страницу")
    public void constructorButtonClickFromCabinetPageTest() {
        new PersonalCabinetPage(driver).headerConstructorButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals("URL Главной Страницы не совпадает с URL в документации", MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Перехожу из личного кабинета в конструктор по клику на логотип")
    @Description("Проверка перехода авторизованного пользователя из ЛК по логотипу и переадресации на главную страницу")
    public void logoClickFromCabinetPageTest() {
        new PersonalCabinetPage(driver).headerLogoClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals("URL Главной Страницы не совпадает с URL в документации", MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Перехожу по кнопке Личный кабинет")
    @Description("Проверяю нажатие на кнопку Личный кабинет и последующую валидную переадресацию в ЛК")
    public void openCabinetPageTest() {
        new MainPage(driver).headerLoginButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(PERSONAL_CABINET_PAGE_URL));
        assertEquals("URL Личного Кабинета не совпадает с URL в документации", PERSONAL_CABINET_PAGE_URL, driver.getCurrentUrl());
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {
        userAPI.deleteUser(accessToken);
        WebDriverSetup.stopDriver();
    }
}
