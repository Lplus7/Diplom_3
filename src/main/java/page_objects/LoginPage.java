package page_objects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {

    private WebDriver driver;

    private By fieldEmail = By.xpath(".//fieldset[1]//input");

    private By fieldPassword = By.xpath("//input[@name = 'Пароль']");

    private By buttonLogin = By.xpath(".//button[text()='Войти']");


    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Прохожу авторизацию на странице входа")
    public void login(String email, String password) {
        driver.findElement(buttonLogin).isDisplayed();
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonLogin).click();
    }

}
