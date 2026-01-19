package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Класс страницы с товарами (Inventory).
 * Появляется после успешного логина.
 */
public class InventoryPage {
    private WebDriver driver;

    // Локатор заголовка страницы
    private By title = By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Проверить, отображается ли заголовок страницы")
    public boolean isTitleDisplayed() {
        try {
            return driver.findElement(title).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
