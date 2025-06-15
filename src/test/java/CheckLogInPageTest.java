import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.LogInPage;
import ru.praktikum.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CheckLogInPageTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private RegistrationPage registrationPage;
    private LogInPage logInPage;

    @Before
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        logInPage = new LogInPage(driver);
        name = registrationPage.returnRandomName();
        email = registrationPage.returnRandomEmail();
        password = registrationPage.returnRandomPassword();
        registrationPage.processRegistration(name, email,password);
    }

    @Test
    public void logInFromInMainPage() {
        logInPage.openLoginPageFromMainPage();
        logInPage.fillLogInForm(email,password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    public void logInFromPersonalAccount() {
        logInPage.openPersonalAccount();
        logInPage.fillLogInForm(email,password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    public void logInFromFormRegistration() {
        logInPage.openPageRegister();
        logInPage.openLoginPageFromRegisterPage();
        logInPage.fillLogInForm(email,password);assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    public void logInFromFormForgotPassword() {
        logInPage.recoverPasswordPage();
        logInPage.openLoginPageFromRecoverPasswordPage();
        logInPage.fillLogInForm(email,password);assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @After
    public void  tearDown() {
        driver.quit();
    }
}