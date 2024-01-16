package ru.retail;


import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWebFormNegative {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    User unknownUser = new User(
            "sdfsdfsdfs@rambler1.ru",
            "sdfsdfd2332sdf23"
    );

    //@Disabled
    @Test
    void negativeScriptAuthentication() {
        //Заходим на страницу и проверяем, что элемент с текстом "Вход в систему" отображается
        driver.get("https://ds.retail-soft.pro/#/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement LogIn = driver.findElement(By.className("fw-normal"));
        assertEquals(true, LogIn.isDisplayed());

        //Находим поля логина и пароля, вбиваем в них невалидные данные
        driver.findElement(By.id("floatingInput")).sendKeys(unknownUser.getEmail());
        driver.findElement(By.id("floatingPassword")).sendKeys(unknownUser.getPass());
        //Нажимаем кнопку "Войти"
        driver.findElement(By.className("btn-primary")).click();
        //Проверяем, что получили элемент с текстом ошибки
        String expected = "Ошибка входа";
        String actual = driver.findElement(By.className("alert-danger")).getText().trim();
        assertEquals(expected, actual);
    }
}