package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    private WebDriver driver;
    public By loginButtonToPersonalAccountOnMainPage = By.xpath("//p[text()='Личный Кабинет']");
    public By loginButtonOnMainPage = By.className("button_button_type_primary__1O7Bx");
    public By loginButtonOnRegisterPage = By.className("Auth_link__1fOlj");
    public By loginButtonOnPageRecoverPassword = By.className("Auth_link__1fOlj");
    public By enterButtonOnEnterPage = By.className("button_button__33qZ0");
    public By emailFieldLocator = By.name("name");
    public By passwordFieldLocator = By.name("Пароль");
    public By textAfterLogInOnProfile = By.xpath("//a[text()='Профиль']");


    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPageFromMainPage() {
        driver.findElement(loginButtonOnMainPage).click();
    }

    public void openPersonalAccount() {
        driver.findElement(loginButtonToPersonalAccountOnMainPage).click();
    }

    public void openPageRegister() {
            driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    public void openLoginPageFromRegisterPage() {
            driver.findElement(loginButtonOnRegisterPage).click();
    }

    public void recoverPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    public void openLoginPageFromRecoverPasswordPage() {
            driver.findElement(loginButtonOnPageRecoverPassword).click();
        }

    public void fillEmailOmLogInPage (String email) {
            driver.findElement(emailFieldLocator).sendKeys(email);
    }

    public void fillPasswordOmLogInPage (String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    public void clickButtonLogIn() {
        driver.findElement(enterButtonOnEnterPage).click();
    }

    public String returnTextAfterLogInOnProfile() {
        return driver.findElement(textAfterLogInOnProfile).getText();
    }

    public void fillLogInForm(String email, String password) {
        fillEmailOmLogInPage(email);
        fillPasswordOmLogInPage(password);
        clickButtonLogIn();
        openPersonalAccount();
    }
}