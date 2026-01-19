# Автоматизация тестирования SauceDemo (Java + Selenium)

Тестовое задание на позицию QA Automation Engineer.

## Стек технологий
- **Java 17**
- **Selenium WebDriver** (UI тестирование)
- **JUnit 5** (Тестовый фреймворк)
- **Maven** (Сборщик проекта)
- **Allure Reports** (Отчетность)
- **Log4j2** (Логирование)
- **WebDriverManager** (Управление драйверами браузеров)

## Особенности проекта
- Реализован паттерн **Page Object Model (POM)**.
- Использованы аннотации Allure (`@Step`, `@Epic`, `@Feature`, `@Severity`) для детальных отчетов.
- Настроено логирование.
- Поддержка нескольких браузеров (Chrome, Firefox).

## Как запустить тесты

### Предварительные требования
1. Установленный **JDK 17** или выше.
2. Установленный **Maven**.
3. Браузер **Chrome** или **Firefox**.

### Запуск тестов из терминала
Для запуска всех тестов в Chrome (по умолчанию):
```bash
mvn clean test
```

Для запуска в Firefox:
```bash
mvn clean test -Dbrowser=firefox
```

## Генерация отчетов Allure
После выполнения тестов результаты сохраняются в папку `target/allure-results`.

Чтобы открыть красивый отчет в браузере:
1. Установите Allure commandline (если еще не установлен).
2. Выполните команду:
```bash
mvn allure:serve
```

## Структура проекта
- `src/main/java/com/saucedemo/pages` — Page Objects (описание страниц).
- `src/test/java/com/saucedemo/tests` — Тестовые сценарии и BaseTest.
- `src/test/resources` — Конфигурационные файлы (логирование).
