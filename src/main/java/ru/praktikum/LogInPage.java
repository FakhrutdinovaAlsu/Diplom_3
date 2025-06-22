package ru.praktikum;

import io.qameta.allure.Step;
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

    @Step("Открытие страницы https://stellarburgers.nomoreparties.site")
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Открытие страницы авторизации через главную страницу")
    public void openLoginPageFromMainPage() {
        driver.findElement(loginButtonOnMainPage).click();
    }

    @Step("Открытие страницы авторизации через личный кабинет")
    public void openPersonalAccount() {
        driver.findElement(loginButtonToPersonalAccountOnMainPage).click();
    }

    @Step("Открытие страницы регистрации")
    public void openPageRegister() {
            driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Открытие страницы авторизации через страницу регистрации")
    public void openLoginPageFromRegisterPage() {
            driver.findElement(loginButtonOnRegisterPage).click();
    }

    @Step("Открытие страницы восстановления пароля")
    public void recoverPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Открытие страницы авторизации через страницу восстановления пароля")
    public void openLoginPageFromRecoverPasswordPage() {
            driver.findElement(loginButtonOnPageRecoverPassword).click();
        }

    @Step("Заполнить поле Email")
    public void fillEmailOmLogInPage (String email) {
            driver.findElement(emailFieldLocator).sendKeys(email);
    }

    @Step("Заполнить поле пароля")
    public void fillPasswordOmLogInPage (String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Нажать на кнопку авторизации")
    public void clickButtonLogIn() {
        driver.findElement(enterButtonOnEnterPage).click();
    }

    @Step("Нажать на кнопку авторизации")
    public String returnTextAfterLogInOnProfile() {
        return driver.findElement(textAfterLogInOnProfile).getText();
    }

    @Step("Заполнение формы авторизации")
    public void fillLogInForm(String email, String password) {
        fillEmailOmLogInPage(email);
        fillPasswordOmLogInPage(password);
        clickButtonLogIn();
        openPersonalAccount();
    }
}