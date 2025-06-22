package ru.praktikum;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    public By registerButtonOnMainPage = By.xpath("//p[text()='Личный Кабинет']");
    public By wayToRegistrationPage = By.xpath("//a[text()='Зарегистрироваться']");
    public By nameFieldLocator = By.cssSelector("input[name='name']");
    public By passwordFieldLocator = By.cssSelector("input[name='Пароль']");
    public By buttonRegister = By.className("button_button__33qZ0");
    public By afterRegister = By.xpath("//h2[text()='Вход']");
    public By errorPasswordText = By.className("input__error");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу https://stellarburgers.nomoreparties.site/")
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Открыть страницу авторизации")
    public void openLoginPage() {
        driver.findElement(registerButtonOnMainPage).click();
    }

    @Step("Открыть страницу регистрации")
    public void openRegistrationPage() {
        driver.findElement(wayToRegistrationPage).click();
    }

    @Step("Заполнить имя")
    public void fillFieldName(String name) {
        driver.findElement(nameFieldLocator).sendKeys(name);
    }

    @Step("Заполнить email")
    public void fillFieldEmail(String email) {
        getEmailInput().sendKeys(email);
    }

    @Step("Заполнить пароль")
    public void fillFieldPassword(String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Нажать на кнопку регистрации")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    private WebElement getEmailInput() {
        List<WebElement> list = driver.findElements(nameFieldLocator);
        return list.get(1);
    }

    @Step("Вернуть текст на странице после реигстрации")
    public String returnTextAfterRegister() {
        return driver.findElement(afterRegister).getText();
    }

    @Step("Получить текст ошибки при заполнении невалидного пароля")
    public String returnTextAfterEnterIncorrectPassword() {
        fillFieldPassword("123");
        driver.findElement(nameFieldLocator).click();
        return driver.findElement(errorPasswordText).getText();
    }
}