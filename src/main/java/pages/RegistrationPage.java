package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private final WebDriver driver;
    private By inputName = By.xpath("//input[@name='name']");
    private By inputEmail = By.xpath("//label[contains(text(), 'Email')]/../input");
    private By inputPassword = By.xpath("//input[@name='Пароль']");
    private By registrationButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private By signInButton = By.xpath("//a[@href='/login']");
    private By errorMessage = By.xpath("//p[contains(text(),'Некорректный пароль')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInputName() {
        return driver.findElement(inputName);
    }

    public void setInputName(String text) {
        getInputName().sendKeys(text);
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

    public void registration(String name, String email, String password) {
        setInputName(name);
        setInputEmail(email);
        setInputPassword(password);
        clickToRegistrationButton();
    }

    public void clickToRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickToSignInButton() {
        driver.findElement(signInButton).click();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }





}
