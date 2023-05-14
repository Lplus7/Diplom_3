package page_objects;
import utility.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class PersonalCabinetPage {
    private WebDriver driver;
    private By buttonConstructor = By.xpath(".//header//p[text()='Конструктор']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By buttonExit = By.xpath(".//button[text()='Выход']");

    public PersonalCabinetPage (WebDriver driver) {

        this.driver = driver;
    }

    @Step("Нажимаю на кнопку Конструктор в хедере")
    public void headerConstructorButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(buttonConstructor));
        driver.findElement(buttonConstructor).click();
    }

    @Step("Нажимаю на логотип в хедере")
    public void headerLogoClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(logo));
        driver.findElement(logo).click();
    }

    @Step("Нажимаю на кнопку Выход")
    public void logoutButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(buttonExit));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonExit));
        driver.findElement(buttonExit).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(Constants.LOGIN_PAGE_URL));
    }

}
