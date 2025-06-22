package ru.praktikum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserFactory {
    private static final Properties properties = new Properties();
    private static final String YANDEX_DRIVER_PATH = "C:\\Users\\alsou\\Desktop\\YD\\yandexdriver.exe";
    private static final String DEFAULT_YANDEX_PATH =
            "C:\\Users\\alsou\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    static {
        try (InputStream input = BrowserFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser",
                        properties.getProperty("browser", "chrome"))
                .toLowerCase();
        System.out.println("=== DEBUG ===");
        System.out.println("Using browser: " + browser);
        System.out.println("System properties: " + System.getProperties());
        System.out.println("Config properties: " + properties);
        System.out.println("=== DEBUG ===");
        WebDriver driver;
        switch (browser) {
            case "yandex":
                driver = startYandexBrowser();
                break;
            case "chrome":
            default:
                driver = startChrome();
        }
        return driver;
    }

    private static WebDriver startChrome() {
        WebDriverManager.chromedriver()
                .driverVersion("137.0.7151.119")
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-blink-features=AutomationControlled",
                "--disable-logging",
                "--log-level=3"
        );
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver startYandexBrowser() {
        // Полное отключение логов WebDriver
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);

        // Указываем путь к драйверу Яндекс.Браузера
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(DEFAULT_YANDEX_PATH);
        options.addArguments(
                "--remote-allow-origins=*",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-blink-features=AutomationControlled",
                "--disable-logging",
                "--log-level=3"  // Минимальный уровень логов
        );

        // Полное отключение DevTools логов
        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation", "enable-logging"});

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}