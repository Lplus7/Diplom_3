package page_objects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
public class MainPage {
    private WebDriver driver;
    private By buttonLogin = By.xpath("//*[.='Войти в аккаунт']");
    private By buttonMakeOrder = By.xpath("//*[.='Оформить заказ']");
    private By headerButtonLogin = By.linkText("Личный Кабинет");
    private By constructorButtonBuns = By.xpath(".//div/span[text()='Булки']");
    private By constructorButtonSauces = By.xpath(".//div/span[text()='Соусы']");
    private By constructorButtonFillings = By.xpath(".//div/span[text()='Начинки']");
    private By constructorActiveTab = By.className("tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Нажимаю на кнопку Войти в аккаунт")
    public void loginButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
              .until(ExpectedConditions.elementToBeClickable(buttonLogin));
        WebElement element = driver.findElement(buttonLogin);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonLogin).click();
    }

    @Step("Нажимаю на кнопку Личный кабинет в хедере")
    public void headerLoginButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(headerButtonLogin));
        WebElement element = driver.findElement(headerButtonLogin);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(headerButtonLogin).click();
    }

    @Step("Нажимаю на кнопку Булки в конструкторе")
    public void constructorBunsButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(constructorButtonBuns));
        WebElement element = driver.findElement(constructorButtonBuns);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(constructorButtonBuns).click();
    }

    @Step("Нажимаю на кнопку Соусы в конструкторе")
    public void constructorSaucesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(constructorButtonSauces));
        WebElement element = driver.findElement(constructorButtonSauces);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(constructorButtonSauces).click();
    }

    @Step("Нажимаю на кнопку Начинки в конструкторе")
    public void constructorFillingsButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(constructorButtonFillings));
        WebElement element = driver.findElement(constructorButtonFillings);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(constructorButtonFillings).click();
    }

    @Step("Получаю активную вкладку в конструкторе")
    public String getConstructorActiveTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5));
        return driver.findElement(constructorActiveTab).getText();
    }

}
