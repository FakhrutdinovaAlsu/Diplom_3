import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.BrowserFactory;
import ru.praktikum.RegistrationPage;
import ru.praktikum.UserSteps;

import java.time.Duration;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckRegistrationTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private RegistrationPage registrationPage;
    private UserSteps userSteps = new UserSteps();
    private final String browserName;

    public CheckRegistrationTest(String browserName) {
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
    public void StartUp()  {
        driver = BrowserFactory.getDriver(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        name = UserSteps.returnRandomName();
        email = UserSteps.returnRandomEmail();
        password = UserSteps.returnRandomPassword();
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
    public void tearDown() {
        try {
            // Авторизация только если регистрация прошла успешно
            if (driver.getCurrentUrl().contains("login")) {
                String accessToken = userSteps.loginUser(email, password)
                        .extract()
                        .path("accessToken");
                if (accessToken != null) {
                    userSteps.deleteUser(accessToken);
                }
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}