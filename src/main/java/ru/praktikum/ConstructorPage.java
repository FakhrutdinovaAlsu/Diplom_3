package ru.praktikum;

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

    public void clickButtonBunNonSelected() {
        driver.findElement(buttonBunNotSelected).click();
    }

    public void clickButtonFillingNonSelected() {
        driver.findElement(buttonFillingNotSelected).click();
    }

    public void clickButtonSauceNonSelected() {
        driver.findElement(buttonSauceNotSelected).click();
    }

    public String returnTextFromActiveBun() {
        return driver.findElement(buttonSelected).getText();
    }
}