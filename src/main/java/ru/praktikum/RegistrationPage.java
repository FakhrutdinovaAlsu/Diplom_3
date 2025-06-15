package ru.praktikum;

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

    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void openLoginPage() {
        driver.findElement(registerButtonOnMainPage).click();
    }

    public void openRegistrationPage() {
        driver.findElement(wayToRegistrationPage).click();
    }

    public void processRegistration(String name, String email, String password) {
        openPage();
        openLoginPage();
        openRegistrationPage();
        fillFieldName(name);
        fillFieldEmail(email);
        fillFieldPassword(password);
        driver.findElement(buttonRegister).click();
        openPage();
    }

    public void fillFieldName(String name) {
        driver.findElement(nameFieldLocator).sendKeys(name);
    }

    public void fillFieldEmail(String email) {
        getEmailInput().sendKeys(email);
    }

    public void fillFieldPassword(String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
        ;
    }

    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    public String returnRandomName() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String returnRandomPassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String returnRandomEmail() {
        return RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
    }

    private WebElement getEmailInput() {
        List<WebElement> list = driver.findElements(nameFieldLocator);
        return list.get(1);
    }

    public String returnTextAfterRegister() {
        return driver.findElement(afterRegister).getText();
    }

    public String returnTextAfterEnterIncorrectPassword() {
        fillFieldPassword("123");
        driver.findElement(nameFieldLocator).click();
        return driver.findElement(errorPasswordText).getText();
    }
}