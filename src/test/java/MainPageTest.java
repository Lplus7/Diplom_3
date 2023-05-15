import TestBasis.TestFlow;
import org.openqa.selenium.By;
import page_objects.MainPage;
import utility.Constants;
import utility.WebDriverSetup;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainPageTest extends TestFlow {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    @Description("Инициализация данных")
    public void setUp() {
        driver = WebDriverSetup.runDriver();
        driver.get(Constants.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Проверяю переход к разделу Булки")
    @Description("Проверка успешного скролла ко вкладке Булки")
    public void constructorBunButtonClickTest() {
        if (mainPage.getConstructorActiveTab().equals("Булки")) {
            mainPage.constructorSaucesButtonClick();
        }
        mainPage.constructorBunsButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Булки']")).isDisplayed()));
    }

    @Test
    @DisplayName("Проверяю переход к разделу Начинки")
    @Description("Проверка успешного скролла ко вкладке Начинки")
    public void constructorMainButtonClickTest() {
        if (mainPage.getConstructorActiveTab().equals("Начинки")) {
            mainPage.constructorSaucesButtonClick();
        }
        mainPage.constructorFillingsButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Начинки']")).isDisplayed()));
    }

    @Test
    @DisplayName("Проверяю переход к разделу Соусы")
    @Description("Проверка успешного скролла ко вкладке Соусы")
    public void constructorSauceButtonClickTest() {
        if(mainPage.getConstructorActiveTab().equals("Соусы")) {
            mainPage.constructorFillingsButtonClick();
        }
        mainPage.constructorSaucesButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Соусы']")).isDisplayed()));
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {

        WebDriverSetup.stopDriver();
    }

}
