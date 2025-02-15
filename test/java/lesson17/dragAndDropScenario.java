package lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;
import static org.testng.Assert.assertTrue;

public class dragAndDropScenario {
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
        driver.findElement(By.xpath("//div[contains(text(), 'Drag & Drop')]")).click();
        Thread.sleep(2000);

        Actions actions = new Actions(driver);  // Создаём объект Actions

        WebElement source1 = driver.findElement(By.id("manual1"));
        WebElement target1 = driver.findElement(By.id("target-manual1"));
        actions.dragAndDrop(source1, target1).perform(); // Перетаскиваем source -> target

        WebElement source2 = driver.findElement(By.id("manual2"));
        WebElement target2 = driver.findElement(By.id("target-manual2"));
        actions.dragAndDrop(source2, target2).perform();

        WebElement source3 = driver.findElement(By.id("auto1"));
        WebElement target3 = driver.findElement(By.id("target-auto1"));
        actions.dragAndDrop(source3, target3).perform();

        WebElement source4 = driver.findElement(By.id("auto2"));
        WebElement target4 = driver.findElement(By.id("target-auto2"));
        actions.dragAndDrop(source4, target4).perform();

        Thread.sleep(3000);
        assertTrue(isDragNDropSuccessful(), "Сценарий завершён с ошибкой");

    }
    private boolean isDragNDropSuccessful() {
        try {
            driver.findElement(By.xpath("//div[contains(text(), 'Congratulations!')]"));
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
