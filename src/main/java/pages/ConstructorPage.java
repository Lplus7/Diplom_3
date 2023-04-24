package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;
    private By bunsTab = By.xpath("//span[contains(text(), 'Булки')]");
    private By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]");
    private By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]");
    private By bunsHeader = By.xpath("//h2[contains(text(), 'Булки')]");
    private By saucesHeader = By.xpath("//h2[contains(text(), 'Соусы')]");
    private By fillingsHeader = By.xpath("//h2[contains(text(), 'Начинки')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickToSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickToFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public void checkBunsHeader() {
        driver.findElement(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[contains(text(), 'Булки')]")).isDisplayed();
        driver.findElement(bunsHeader).isDisplayed();
    }

    public void checkSaucesHeader() {
        driver.findElement(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[contains(text(), 'Соусы')]")).isDisplayed();
        driver.findElement(saucesHeader).isDisplayed();
    }

    public void checkFillingsHeader() {
        driver.findElement(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[contains(text(), 'Начинки')]")).isDisplayed();
        driver.findElement(fillingsHeader).isDisplayed();
    }


}
