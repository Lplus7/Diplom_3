import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;

public class SignInTests extends Base {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("В данном сценарии проверяется вход по кнопке «Войти в аккаунт» на главной странице")
    public void signInUsingTheSignInButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToSignInButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.getPlaceAnOrderButton().isDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("В данном сценарии проверяется вход через кнопку «Личный кабинет»")
    public void signInViaTheAccountButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.getPlaceAnOrderButton().isDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("В данном сценарии проверяется вход через кнопку в форме регистрации")
    public void signInViaTheButtonInTheRegistrationForm() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.clickToRegistrationButton();
        registrationPage.clickToSignInButton();
        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.getPlaceAnOrderButton().isDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("В данном сценарии проверяется вход через кнопку в форме восстановления пароля")
    public void signInViaTheButtonInThePasswordRecoveryForm() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.clickRetrievePasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickSignInButton();
        signInPage.getSignInButton().isDisplayed();
        signInPage.signIn(user.getEmail(), user.getPassword());

        homePage.getPlaceAnOrderButton().isDisplayed();
    }
}
