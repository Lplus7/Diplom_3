package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private By inputEmail = By.xpath("//input[@name='name']");
    private By retrieveThePasswordButton = By.xpath("//button[contains(text(), 'Восстановить')]");
    private By signInButton = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInputEmail() {
        return driver.findElement(inputEmail);
    }

    public void setInputEmail() {
        getInputEmail().sendKeys();
    }

    public WebElement getRetrieveThePasswordButton() {
        return driver.findElement(retrieveThePasswordButton);
    }

    public WebElement getSignInButton() {
        return driver.findElement(signInButton);
    }

    public SignInPage clickSignInButton() {
        getSignInButton().click();
        return new SignInPage(driver);
    }

}
