import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CheckLogInPageTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private RegistrationPage registrationPage;
    private LogInPage logInPage;
    private UserSteps userSteps = new UserSteps();

    @Before
    public void StartUp() {
        driver = BrowserFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        RestAssured.baseURI = ApiConfig.getBaseUrl();
        email = userSteps.returnRandomEmail();
        password = userSteps.returnRandomPassword();
        name = userSteps.returnRandomName();
        userSteps.createUser(email, password, name);
        logInPage = new LogInPage(driver);
        logInPage.openPage();
    }

    @Test
    @Description("Проверить вход по кнопке Войти в аккаунт")
    public void logInFromInMainPage() {
        logInPage.openLoginPageFromMainPage();
        logInPage.fillLogInForm(email, password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    @Description("Проверить вход по кнопке Линчый кабинет")
    public void logInFromPersonalAccount() {
        logInPage.openPersonalAccount();
        logInPage.fillLogInForm(email,password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    @Description("Проверить вход через кнопку на форме регистрации")
    public void logInFromFormRegistration() {
        logInPage.openPageRegister();
        logInPage.openLoginPageFromRegisterPage();
        logInPage.fillLogInForm(email,password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @Test
    @Description("Проверить вход в окне восстановления пароля")
    public void logInFromFormForgotPassword() {
        logInPage.recoverPasswordPage();
        logInPage.openLoginPageFromRecoverPasswordPage();
        logInPage.fillLogInForm(email,password);
        assertEquals("Профиль", logInPage.returnTextAfterLogInOnProfile());
    }

    @After
    public void  tearDown() {
        String accessToken = userSteps.loginUser(email, password).extract().path("accessToken");
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}