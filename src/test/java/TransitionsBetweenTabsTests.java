import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.ConstructorPage;

public class TransitionsBetweenTabsTests extends Base {

    @Test
    @DisplayName("Проверка перехода на вкладку «Булки»")
    @Description("В данном сценарии проверяется, что выполняется переход на вкладку «Булки»")
    public void transitionToTheBunsTab() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickToFillingsTab();
        constructorPage.clickToBunsTab();
        constructorPage.checkBunsHeader();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку «Соусы»")
    @Description("В данном сценарии проверяется, что выполняется переход на вкладку «Соусы»")
    public void transitionToTheSaucesTab() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickToSaucesTab();
        constructorPage.checkSaucesHeader();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку «Начинки»")
    @Description("В данном сценарии проверяется, что выполняется переход на вкладку «Начинки»")
    public void transitionToTheFillingsTab() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickToFillingsTab();
        constructorPage.checkFillingsHeader();
    }




}
