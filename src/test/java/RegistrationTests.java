import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;


public class RegistrationTests extends Base {

    @Test
    @DisplayName("Регистрация с валидными данными")
    @Description("В данном сценарии проверяется, что пользователь может быть зарегистрирован с валидными данными")
    public void registrationTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        isTheUserRegistered = true;

        signInPage.getSignInButton().isDisplayed();
    }

    @Test
    @DisplayName("Регистрация с паролем короче шести символов")
    @Description("В данном сценарии проверяется, что пользователь не может быть зарегистрирован с паролем менее шести символов")
    public void registrationWithShortPasswordTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickToAccountButton();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickToRegistrationButton();

        user.setPassword("12345");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.getErrorMessage();
    }


}
