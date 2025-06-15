import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.ConstructorPage;
import ru.praktikum.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorPageTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private ConstructorPage constructorPage;

    @Before
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
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
