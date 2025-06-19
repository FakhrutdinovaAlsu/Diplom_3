import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.BrowserFactory;
import ru.praktikum.ConstructorPage;
import ru.praktikum.RegistrationPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConstructorPageTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private ConstructorPage constructorPage;
    private final String browserName;

    public ConstructorPageTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters(name = "BrowserName")
    public static Object[][] browsers() {
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }

    @Before
    public void StartUp() {
        driver = BrowserFactory.getDriver(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    public void checkActiveButtonBun() {
        registrationPage.openPage();
        constructorPage.clickButtonSauceNonSelected();
        constructorPage.clickButtonBunNonSelected();
        assertEquals("Булки",constructorPage.returnTextFromActiveBun());
    }

    @Test
    public void checkActiveButtonSauce() {
        registrationPage.openPage();
        constructorPage.clickButtonSauceNonSelected();
        assertEquals("Соусы",constructorPage.returnTextFromActiveBun());
    }

    @Test
    public void checkActiveButtonFilling() {
        registrationPage.openPage();
        constructorPage.clickButtonFillingNonSelected();
        assertEquals("Начинки",constructorPage.returnTextFromActiveBun());
    }

    @After
    public void  tearDown() {
        driver.quit();
    }
}
