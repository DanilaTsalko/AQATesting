package lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertTrue;

public class selectScenario {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void startScenario() throws InterruptedException {
        setup();
        // Переход на сайт
        driver.get("https://qa-course-01.andersenlab.com/login");
        Thread.sleep(2000);
        driver.findElement(By.name("email")).sendKeys("example@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Sign in']")).click();
        Thread.sleep(2000);

        WebElement hoverable = driver.findElement(By.xpath("//div[contains(text(), 'AQA')]"));
        new Actions(driver).moveToElement(hoverable).perform();
        driver.findElement(By.xpath("//div[contains(text(), 'Select')]")).click();
        Thread.sleep(2000);

        // ➊ Выбор страны (USA)
        Select countryDropdown = new Select(driver.findElement(By.cssSelector("[data-lol='SelectCountry']")));
        countryDropdown.selectByVisibleText("USA");
        Thread.sleep(1000);

        // ➋ Выбор языка (English)
        WebElement languageDropdown = driver.findElement(By.id("SelectLanguage"));
        new Select(languageDropdown).selectByVisibleText("English");
        Thread.sleep(1000);

        // ➌ Выбор типа (Testing)
        WebElement typeDropdown = driver.findElement(By.cssSelector("[data-doubtful-but-ok='SelectType']"));
        new Select(typeDropdown).selectByVisibleText("Testing");
        Thread.sleep(1000);


        // Вычисление следующего понедельника
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        if (today.getDayOfWeek().getValue() == 1) {
            startDate = today.plusDays(7);  // Если сегодня понедельник — берём следующий
        } else {
            startDate = today.plusDays((8 - today.getDayOfWeek().getValue()) % 7);
        }

        LocalDate lastDate = startDate.plusWeeks(2); // Через 2 недели

        // Форматирование даты (YYYY-MM-DD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateFormatted = startDate.format(formatter);
        String lastDateFormatted = lastDate.format(formatter);

        // ➍ Установка Start Date
        WebElement startDateInput = driver.findElement(By.cssSelector("[data-calendar='1']"));
        startDateInput.sendKeys(startDateFormatted);
        Thread.sleep(1000);

        // ➎ Установка End Date
        WebElement endDateInput = driver.findElement(By.cssSelector("[data-calendar='2']"));
        endDateInput.sendKeys(lastDateFormatted);
        Thread.sleep(1000);

        // Найдём селектор по id
        WebElement coursesDropdown = driver.findElement(By.id("MultipleSelect"));

        // Используем класс Select
        Select select = new Select(coursesDropdown);
        select.deselectAll(); // Очищаем выбор, если он уже есть
        select.selectByVisibleText("AQA Java");
        select.selectByVisibleText("AQA Python");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(2000);



        assertTrue(isSearchSuccessful(), "Сценарий завершён с ошибкой");

    }
    private boolean isSearchSuccessful() {
        try {
            driver.findElement(By.xpath("//h2[text()='Unfortunately, we did not find any courses matching your chosen criteria.']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
