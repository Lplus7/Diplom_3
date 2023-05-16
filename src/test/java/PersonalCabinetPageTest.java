import TestBasis.TestFlow;
import org.openqa.selenium.By;
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
import static org.junit.Assert.*;
import static utility.Constants.*;

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
        assertNotEquals("Пользователь всё ещё находится в Личном Кабинете, выход не произошёл", PERSONAL_CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Авторизации, или её URL отличается от указанного в документации", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяю переход по кнопке Конструктор из Личного Кабинета")
    @Description("Проверка перехода авторизованного пользователя из ЛК по кнопке Конструктор и переадресации на главную страницу")
    public void constructorButtonClickFromCabinetPageTest() {
        new PersonalCabinetPage(driver).headerConstructorButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        boolean isConstructorButtonAvailable = driver.findElement(By.xpath(".//header//p[text()='Конструктор']")).isDisplayed() && driver.findElement(By.xpath(".//header//p[text()='Конструктор']")).isEnabled();
        assertNotEquals("Пользователь всё ещё находится в Личном Кабинете, переход не произошёл", PERSONAL_CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на Главную страницу, или её URL отличается от указанного в документации", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Конструктор не прогрузилась или не кликабельна", true, isConstructorButtonAvailable);
    }

    @Test
    @DisplayName("Перехожу из личного кабинета в конструктор по клику на логотип")
    @Description("Проверка перехода авторизованного пользователя из ЛК по логотипу и переадресации на главную страницу")
    public void logoClickFromCabinetPageTest() {
        new PersonalCabinetPage(driver).headerLogoClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        boolean isLogoAvailable = driver.findElement(By.className("AppHeader_header__logo__2D0X2")).isDisplayed() && driver.findElement(By.className("AppHeader_header__logo__2D0X2")).isEnabled();
        assertNotEquals("Пользователь всё ещё находится в Личном Кабинете, переход не произошёл", PERSONAL_CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на Главную страницу, или её URL отличается от указанного в документации", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Логотип не прогрузился или не кликабелен", true, isLogoAvailable);
    }

    @Test
    @DisplayName("Перехожу по кнопке Личный кабинет")
    @Description("Проверяю нажатие на кнопку Личный кабинет и последующую переадресацию в ЛК")
    public void openCabinetPageTest() {
        new MainPage(driver).headerLoginButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(PERSONAL_CABINET_PAGE_URL));
        boolean isPersonalCabinetButtonAvailable = driver.findElement(By.linkText("Личный Кабинет")).isDisplayed() && driver.findElement(By.linkText("Личный Кабинет")).isEnabled();
        assertNotEquals("Пользователь всё ещё находится на Главной странице, переход не произошёл", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу Личного Кабинета, или её URL отличается от указанного в документации", PERSONAL_CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Личный Кабинет не прогрузилась или не кликабельна", true, isPersonalCabinetButtonAvailable);
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {
        userAPI.deleteUser(accessToken);
        WebDriverSetup.stopDriver();
    }
}
