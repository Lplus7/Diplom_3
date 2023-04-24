package base;

import api.UserClient;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import user.UserGenerator;
import user.UserRegistration;

public class Base {
    public WebDriver driver;
    private final UserGenerator generator = new UserGenerator();
    public UserRegistration user;
    public boolean isTheUserRegistered = false;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        user = generator.randomUser();
    }

    @After
    public void tearDown() {
        if (isTheUserRegistered) {
            String token = UserClient.getToken(user);
            UserClient.deleteUser(token);
        }
        driver.quit();
    }
}
