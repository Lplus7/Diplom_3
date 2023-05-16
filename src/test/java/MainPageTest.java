import TestBasis.TestFlow;
import page_objects.MainPage;
import utility.WebDriverSetup;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;
import static utility.Constants.MAIN_PAGE_URL;

public class MainPageTest extends TestFlow {
    private WebDriver driver;
    private MainPage mainPage;
    private String expectedTab;

    @Before
    @Description("Инициализация данных")
    public void setUp() {
        driver = WebDriverSetup.runDriver();
        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Проверяю переход к разделу Булки")
    @Description("Проверка успешного скролла ко вкладке Булки")
    public void constructorBunButtonClickTest() {
        expectedTab = "Булки";
        if (mainPage.getConstructorActiveTab().equals("Булки")) {
            mainPage.constructorFillingsButtonClick();
        }
        mainPage.constructorBunsButtonClick();
        assertEquals("Вкладка не переключилась, ожидается активной вкладка " + expectedTab, expectedTab, mainPage.getConstructorActiveTab());
    }

    @Test
    @DisplayName("Проверяю переход к разделу Начинки")
    @Description("Проверка успешного скролла ко вкладке Начинки")
    public void constructorMainButtonClickTest() {
        expectedTab = "Начинки";
        if (mainPage.getConstructorActiveTab().equals("Начинки")) {
            mainPage.constructorSaucesButtonClick();
        }
        mainPage.constructorFillingsButtonClick();
        assertEquals("Вкладка не переключилась, ожидается активной вкладка " + expectedTab, expectedTab, mainPage.getConstructorActiveTab());
    }

    @Test
    @DisplayName("Проверяю переход к разделу Соусы")
    @Description("Проверка успешного скролла ко вкладке Соусы")
    public void constructorSauceButtonClickTest() {
        expectedTab = "Соусы";
        if(mainPage.getConstructorActiveTab().equals("Соусы")) {
            mainPage.constructorFillingsButtonClick();
        }
        mainPage.constructorSaucesButtonClick();
        assertEquals("Вкладка не переключилась, ожидается активной вкладка " + expectedTab, expectedTab, mainPage.getConstructorActiveTab());
    }

    @After
    @Description("Очистка данных")
    public void cleanUp() {

        WebDriverSetup.stopDriver();
    }

}
