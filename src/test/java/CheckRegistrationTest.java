import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.RegistrationPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class CheckRegistrationTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private RegistrationPage registrationPage;

    @Before
    public void StartUp()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        name = registrationPage.returnRandomName();
        email = registrationPage.returnRandomEmail();
        password = registrationPage.returnRandomPassword();
    }

    @Test
    public void checkRegistration() {
        registrationPage.openPage();
        registrationPage.openLoginPage();
        registrationPage.openRegistrationPage();
        registrationPage.fillFieldName(name);
        registrationPage.fillFieldEmail(email);
        registrationPage.fillFieldPassword(password);
        registrationPage.clickButtonRegister();
        assertEquals("Вход", registrationPage.returnTextAfterRegister());
    }

    @Test
    public void checkTextAboutIncorrectPassword() {
        registrationPage.openPage();
        registrationPage.openLoginPage();
        registrationPage.openRegistrationPage();
        assertEquals("Некорректный пароль",registrationPage.returnTextAfterEnterIncorrectPassword());
    }

    @After
        public void  tearDown() {
            driver.quit();
    }
}