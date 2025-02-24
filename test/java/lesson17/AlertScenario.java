package lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertTrue;

public class AlertScenario {
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
        driver.findElement(By.xpath("//div[contains(text(), 'Actions')]")).click();
        Thread.sleep(2000);

        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));


        // Работа с первым Alert
        WebElement firstButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]"));
        firstButton.click();
        Thread.sleep(2000);

        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(2000);

        Assert.assertEquals(alert1.getText(), "You have called alert!", "Текст первого Alert не совпадает!");
        alert1.accept();
        Thread.sleep(2000);

        WebElement result = driver.findElement(By.cssSelector("span.font-light"));
        Assert.assertEquals(result.getText(), "Congratulations, you have successfully enrolled in the course!", "Результат первого Alert неверен!");
        Thread.sleep(2000);


        // Работа со вторым Alert
        WebElement secondButton = driver.findElement(By.xpath("//button[contains(text(), 'Get Discount')]")); // Укажите правильный селектор
        new Actions(driver).doubleClick(secondButton).perform();
        Thread.sleep(2000);

        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(2000);

        Assert.assertEquals(alert2.getText(), "Are you sure you want to apply the discount?", "Текст второго Alert не совпадает!");
        alert2.accept();
        Thread.sleep(2000);

        result = driver.findElement(By.cssSelector("span.font-light"));
        Assert.assertEquals(result.getText(), "You received a 10% discount on the second course.", "Результат второго Alert неверен!");


        // Работа со вторым Alert
        WebElement thirdButton = driver.findElement(By.xpath("//button[contains(text(), 'Cancel')]"));
        new Actions(driver).contextClick(thirdButton).perform();
        Thread.sleep(2000);

        Alert alert3 = driver.switchTo().alert();
        alert3.sendKeys("Test");
        alert3.accept();
        Thread.sleep(5000);

        result = driver.findElement(By.cssSelector("span.font-light"));
        Assert.assertEquals(result.getText(), "Your course application has been cancelled. Reason: Test", "Результат третьего Alert неверен!");

    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}