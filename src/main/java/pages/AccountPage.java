package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final WebDriver driver;
    private final By profileButton = By.xpath("//a[@href='/account/profile']");
    private final By orderHistoryButton = By.xpath("//a[@href='/account/order-history']");
    private final By exitButton = By.xpath("//button[contains(text(), 'Выход')]");
    private final By nameField = By.xpath("//input[@name='Name']");
    private final By loginField = By.xpath("//input[@name='name']");
    private final By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]");
    private final By homePageButton = By.xpath("//a[@href='/']");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkProfileButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    public void checkOrderHistoryButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(orderHistoryButton));
    }

    public void checkExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }

    public String getName() {
        WebElement name = driver.findElement(nameField);
        return name.getAttribute("value");
    }

    public String getEmail() {
        WebElement email = driver.findElement(loginField);
        return email.getAttribute("value");
    }

    public void checkButtons() {
        checkProfileButton();
        checkOrderHistoryButton();
        checkExitButton();
    }

    public void clickToConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
        driver.findElement(constructorButton).click();
    }

    public void clickToHomePageButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(homePageButton));
        driver.findElement(homePageButton).click();
    }

}
