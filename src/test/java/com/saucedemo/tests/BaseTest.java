package com.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * Базовый класс для всех тестов.
 * Здесь настраивается запуск и закрытие браузера.
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Читаем название браузера из системных настроек, по умолчанию chrome
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        // Настраиваем браузер
        driver.manage().window().maximize();
        // Неявное ожидание: если элемент не найден сразу, Selenium будет искать его 10 секунд
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            // Закрываем все окна и завершаем сессию драйвера
            driver.quit();
        }
    }
}
