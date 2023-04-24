package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private By registrationButton = By.xpath("//a[@href='/register']");
    private By inputEmail = By.xpath("//input[@name='name']");
    private By inputPassword = By.xpath("//input[@name='Пароль']");
    private By signInButton = By.xpath("//button[contains(text(), 'Войти')]");
    private By retrieveThePasswordButton = By.xpath("//a[@href='/forgot-password']");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegistrationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    public WebElement getSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(signInButton));
        return driver.findElement(signInButton);
    }

    public WebElement getInputEmail() {
        return driver.findElement(inputEmail);
    }

    public void setInputEmail(String text) {
        getInputEmail().sendKeys(text);
    }

    public WebElement getInputPassword() {
        return driver.findElement(inputPassword);
    }

    public void setInputPassword(String text) {
        getInputPassword().sendKeys(text);
    }

    public void clickToSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void signIn(String email, String password) {
        setInputEmail(email);
        setInputPassword(password);
        clickToSignInButton();
    }

    public void clickRetrievePasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(retrieveThePasswordButton));
        WebElement element = driver.findElement(retrieveThePasswordButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(retrieveThePasswordButton).click();
    }
}
