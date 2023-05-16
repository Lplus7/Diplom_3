import TestBasis.TestFlow;
import org.openqa.selenium.By;
import utility.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.WebDriver;
import page_objects.*;

import static org.junit.Assert.assertNotEquals;
import static utility.Constants.*;
import static org.junit.Assert.assertEquals;

public class LoginPageTest extends TestFlow {
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
    }

    @Test
    @DisplayName("Проверяю вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("Проверка авторизации валидными данными из раздела сайта")
    public void mainPageLoginButtonTest() {
        driver.get(MAIN_PAGE_URL);
        boolean isLoginButtonAvailable = driver.findElement(By.xpath("//*[.='Войти в аккаунт']")).isDisplayed() && driver.findElement(By.xpath("//*[.='Войти в аккаунт']")).isEnabled();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.loginButtonClick();
        loginPage.login(email, password);
        assertNotEquals("Пользователь всё ещё находится на Главной странице, переход не произошёл", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Авторизации, или её URL отличается от указанного в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти в аккаунт не прогрузилась или не кликабельна", true, isLoginButtonAvailable);
    }

    @Test
    @DisplayName("Проверяю вход по кнопке «Личный кабинет» в хедере")
    @Description("Проверка авторизации валидными данными из раздела сайта")
    public void headerLoginButtonTest() {
        driver.get(MAIN_PAGE_URL);
        boolean isPersonalCabinetButtonAvailable = driver.findElement(By.linkText("Личный Кабинет")).isDisplayed() && driver.findElement(By.linkText("Личный Кабинет")).isEnabled();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.headerLoginButtonClick();
        loginPage.login(email, password);
        assertNotEquals("Пользователь всё ещё находится на Главной странице, переход не произошёл", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Авторизации, или её URL отличается от указанного в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти в аккаунт не прогрузилась или не кликабельна", true, isPersonalCabinetButtonAvailable);
    }

    @Test
    @DisplayName("Проверяю вход через кнопку в форме регистрации")
    @Description("Проверка авторизации валидными данными из раздела сайта")
    public void registrationLoginButtonTest() {
        driver.get(REGISTRATION_PAGE_URL);
        boolean isLoginButtonAvailable = driver.findElement(By.linkText("Войти")).isDisplayed() && driver.findElement(By.linkText("Войти")).isEnabled();
        new RegistrationPage(driver).loginButtonClick();
        new LoginPage(driver).login(email, password);
        assertNotEquals("Пользователь всё ещё находится на странице Регистрации, переход не произошёл", REGISTRATION_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Авторизации, или её URL отличается от указанного в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти не прогрузилась или не кликабельна", true, isLoginButtonAvailable);

    }

    @Test
    @DisplayName("Проверяю вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации валидными данными из раздела сайта")
    public void passwordRecoveryLoginButtonTest() {
        driver.get(PASSWORD_RECOVERY_PAGE_URL);
        boolean isLoginButtonAvailable = driver.findElement(By.linkText("Войти")).isDisplayed() && driver.findElement(By.linkText("Войти")).isEnabled();
        new PasswordRecoveryPage(driver).loginButtonClick();
        new LoginPage(driver).login(email, password);
        assertNotEquals("Пользователь всё ещё находится на странице Восстановления пароля, переход не произошёл", PASSWORD_RECOVERY_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Авторизации, или её URL отличается от указанного в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти в аккаунт не прогрузилась или не кликабельна", true, isLoginButtonAvailable);
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {
        userAPI.deleteUser(accessToken);
        WebDriverSetup.stopDriver();
    }
}
