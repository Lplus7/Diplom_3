package page_objects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
public class PasswordRecoveryPage {
    private WebDriver driver;
    private By loginButton = By.linkText("Войти");

    public PasswordRecoveryPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Перехожу по кнопке на страницу авторизации")
    public void loginButtonClick() {
        driver.findElement(loginButton).isEnabled();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(loginButton));
        driver.findElement(loginButton).click();
    }

}
