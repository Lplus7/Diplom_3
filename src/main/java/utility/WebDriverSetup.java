package utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverSetup {
    private static WebDriver driver = null;
    private static FileInputStream fileInputStream;
    public static WebDriver runDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        try {
            fileInputStream = new FileInputStream("src/main/resources/browser.properties");
            Properties stream = new Properties();
            stream.load(fileInputStream);

            switch (stream.getProperty("browser.name")) {
                case "yandex":
                    options.setBinary("C:\\Users\\PC\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                    driver = new ChromeDriver(options);
                    break;
                case "chrome":
                default:
                    driver = new ChromeDriver(options);
            }

        } catch (IOException e) {
            e.printStackTrace();
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void stopDriver() {
        driver.quit();
        driver = null;
    }

}
