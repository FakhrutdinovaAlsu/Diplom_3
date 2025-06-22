package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private WebDriver driver;
    public By buttonBunNotSelected = By.xpath("//span[text()='Булки']");
    public By buttonSauceNotSelected = By.xpath("//span[text()='Соусы']");
    public By buttonFillingNotSelected = By.xpath("//span[text()='Начинки']");
    public By buttonSelected = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Нажатие на неактивную вкладку булок")
    public void clickButtonBunNonSelected() {
        driver.findElement(buttonBunNotSelected).click();
    }

    @Step("Нажатие на неактивную вкладку начинок")
    public void clickButtonFillingNonSelected() {
        driver.findElement(buttonFillingNotSelected).click();
    }

    @Step("Нажатие на неактивную вкладку соусов")
    public void clickButtonSauceNonSelected() {
        driver.findElement(buttonSauceNotSelected).click();
    }

    @Step("Получить текст из элемента")
    public String returnTextFromActiveBun() {
        return driver.findElement(buttonSelected).getText();
    }
}