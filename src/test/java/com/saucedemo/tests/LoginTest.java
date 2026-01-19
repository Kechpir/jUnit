package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Тестирование авторизации")
@Feature("Логин на сайте SauceDemo")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        loginPage.open();
    }

    @Test
    @DisplayName("Успешный логин")
    @Description("""
        ### Цель теста:
        Проверить, что пользователь может успешно войти в систему с валидными данными.
        
        ### Входные данные:
        - Логин: `standard_user`
        - Пароль: `secret_sauce`
        
        ### Ожидаемый результат:
        1. Происходит переход на главную страницу (Inventory).
        2. Вверху страницы отображается заголовок "Products".
        """)
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulLogin() {
        loginPage.login("standard_user", "secret_sauce");
        
        Assertions.assertTrue(inventoryPage.isTitleDisplayed(), "Заголовок страницы не отображается!");
        Assertions.assertEquals("Products", inventoryPage.getTitle(), "Текст заголовка не совпадает!");
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    @Description("""
        ### Цель теста:
        Проверить, что при вводе неверного пароля система выдает соответствующую ошибку.
        
        ### Входные данные:
        - Логин: `standard_user`
        - Пароль: `wrong_password`
        
        ### Ожидаемый результат:
        1. Вход в систему не выполняется.
        2. Отображается сообщение об ошибке, содержащее фразу: 'Username and password do not match'.
        """)
    @Severity(SeverityLevel.CRITICAL)
    public void testWrongPassword() {
        loginPage.login("standard_user", "wrong_password");
        
        Assertions.assertTrue(loginPage.isErrorDisplayed(), "Сообщение об ошибке не появилось!");
        Assertions.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), 
                "Текст ошибки не содержит ожидаемую фразу!");
    }

    @Test
    @DisplayName("Логин заблокированного пользователя")
    @Description("""
        ### Цель теста:
        Проверить обработку входа для пользователя, который был заблокирован (locked_out_user).
        
        ### Входные данные:
        - Логин: `locked_out_user`
        - Пароль: `secret_sauce`
        
        ### Ожидаемый результат:
        1. Вход в систему запрещен.
        2. Отображается сообщение об ошибке: 'Sorry, this user has been locked out'.
        """)
    @Severity(SeverityLevel.NORMAL)
    public void testLockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        
        Assertions.assertTrue(loginPage.isErrorDisplayed(), "Сообщение об ошибке не появилось!");
        Assertions.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"), 
                "Текст ошибки для заблокированного пользователя не совпадает!");
    }

    @Test
    @DisplayName("Логин с пустыми полями")
    @Description("""
        ### Цель теста:
        Проверить валидацию полей при попытке входа без ввода логина и пароля.
        
        ### Входные данные:
        - Логин: "" (пусто)
        - Пароль: "" (пусто)
        
        ### Ожидаемый результат:
        1. Система требует заполнения полей.
        2. Отображается сообщение об ошибке: 'Epic sadface: Username is required'.
        """)
    @Severity(SeverityLevel.MINOR)
    public void testEmptyFields() {
        loginPage.clickLogin();
        
        Assertions.assertTrue(loginPage.isErrorDisplayed(), "Сообщение об ошибке не появилось!");
        Assertions.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                "Текст ошибки для пустого логина не совпадает!");
    }

    @Test
    @DisplayName("Логин performance_glitch_user")
    @Description("""
        ### Цель теста:
        Проверить, что пользователь с проблемами производительности может войти в систему, 
        несмотря на искусственную задержку загрузки страницы в 5 секунд.
        
        ### Входные данные:
        - Логин: `performance_glitch_user`
        - Пароль: `secret_sauce`
        
        ### Ожидаемый результат:
        1. Система успешно выполняет вход после паузы.
        2. Страница загружается полностью в пределах установленного таймаута (10 сек).
        """)
    @Severity(SeverityLevel.NORMAL)
    public void testPerformanceGlitchUser() {
        loginPage.login("performance_glitch_user", "secret_sauce");
        
        // Неявное ожидание в BaseTest (10 сек) позволит тесту дождаться загрузки страницы, 
        // несмотря на 5-секундную задержку сайта.
        Assertions.assertTrue(inventoryPage.isTitleDisplayed(), "Страница не загрузилась вовремя!");
        Assertions.assertEquals("Products", inventoryPage.getTitle());
    }
}
