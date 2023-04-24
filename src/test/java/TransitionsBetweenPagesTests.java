import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertEquals;

public class TransitionsBetweenPagesTests extends Base {

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    @Description("В данном сценарии проверяется, что происходит переход по клику на «Личный кабинет» " +
            "и информация в личном кабинете соответствует пользователю")
    public void transitionToAccountPage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.clickToAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.checkButtons();
        assertEquals(user.getName(), accountPage.getName());
        assertEquals(user.getEmail(), accountPage.getEmail());
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    @Description("В данном сценарии проверяется, что происходит переход по клику на «Конструктор» " +
            "и отображается главная страница")
    public void transitionToTheHomePageViaTheConstructorButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.clickToAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickToConstructorButton();
        homePage.getPlaceAnOrderButton().isDisplayed();
    }

    @Test
    @DisplayName("Переход по клику на логотип «Stellar Burgers»")
    @Description("В данном сценарии проверяется, что происходит переход по клику на логотип «Stellar Burgers» " +
            "и отображается главная страница")
    public void transitionToTheHomePageViaTheHomePageButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.clickToAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickToHomePageButton();
        homePage.getPlaceAnOrderButton().isDisplayed();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("В данном сценарии проверяется, что происходит выход из личного кабинета по кнопке «Выйти»")
    public void exitFromAccountViaTheExitButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.clickToAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickExitButton();
        signInPage.getSignInButton().isDisplayed();
    }

}
