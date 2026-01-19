package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс страницы логина.
 * Содержит локаторы элементов и методы для работы с ними.
 */
public class LoginPage {
    private WebDriver driver;

    // 1. Локаторы (адреса элементов)
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorContainer = By.cssSelector(".error-message-container");

    // Конструктор: передаем драйвер из теста в эту страницу
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Методы-действия (что можно делать на странице)

    @Step("Открыть страницу логина")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Ввести логин: {username}")
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    @Step("Ввести пароль: {password}")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку Login")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Выполнить вход пользователем {username}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    @Step("Получить текст ошибки")
    public String getErrorMessage() {
        return driver.findElement(errorContainer).getText();
    }

    @Step("Проверить, отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElements(errorContainer).size() > 0 && 
               driver.findElement(errorContainer).isDisplayed();
    }
}
