package page_objects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
public class RegistrationPage {

    private WebDriver driver;
    private By fieldName = By.xpath(".//fieldset[1]//input");
    private By fieldEmail = By.xpath(".//fieldset[2]//input");
    private By fieldPassword = By.xpath(".//input[@type='password']");
    private By buttonLogin = By.linkText("Войти");
    private By buttonRegistration = By.xpath(".//button[text()='Зарегистрироваться']");
    private By invalidPasswordError = By.className("input__error");
    public RegistrationPage (WebDriver driver) {

        this.driver = driver;
    }

    @Step("Заполняю и отправляю форму Регистрации")
    public void registrationFormFilling (String email, String password, String name) {
        driver.findElement(fieldName).isEnabled();
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).isEnabled();
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).isEnabled();
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonRegistration).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
    }

    @Step("Проверяю отображение ошибки некорректного пароля")
    public boolean isIncorrectPasswordErrorDisplayed () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(invalidPasswordError));
        return driver.findElement(invalidPasswordError).isDisplayed();
    }

    @Step("Нажимаю на кнопку авторизации")
    public void loginButtonClick () {
        driver.findElement(buttonLogin).isEnabled();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonLogin));
        driver.findElement(buttonLogin).click();
    }

}
