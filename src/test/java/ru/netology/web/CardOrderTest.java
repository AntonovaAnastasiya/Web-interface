package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CardOrderTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

//    @BeforeAll
//    public static void setUpAll() {
//        if (System.getProperty("os.name").contains("Linux")) {
//            System.setProperty("webdriver.chrome.driver", "driver/linux/chromedriver");
//        } else {
//            System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
//        }
//    }

    @BeforeEach
    public void setUp1() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

//    @BeforeEach
//    void setUp2() {
//        driver = new ChromeDriver();
//    }


    @AfterEach
    void close() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Анастасия Антонова");
        elements.get(1).sendKeys("+79146562545");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestV2() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Анастасия-Виктория Петровна");
        elements.get(1).sendKeys("+70000000002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldCheckValidationName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анастасия-ВиктоRQрия Петровна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000000002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldCheckValidationPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анастасия-Виктория Петровна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000гшнгнк0002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

//    @Test
//    void shouldCheckValidationCheckboxText() {
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анастасия-Виктория Петровна");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000000002");
//        driver.findElement(By.className("button")).click();
//        String text = driver.findElement(By.cssSelector(".input_invalid .checkbox__text")).getText();
//        Assertions.assertTrue(driver.findElement(By.cssSelector(".input_invalid .checkbox__text")).getCssValue("color").equals("#ff5c5c!important"));
//    }
}


