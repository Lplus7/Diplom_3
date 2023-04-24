package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {
    private final WebDriver driver;
    private final By accountButton = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private final By signInButton = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    private final By placeAnOrderButton = By.xpath("//button[contains(text(), 'Оформить заказ')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(accountButton));
        driver.findElement(accountButton).click();
    }

    public void clickToSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }

    public WebElement getPlaceAnOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(placeAnOrderButton));
        return driver.findElement(placeAnOrderButton);
    }
}
