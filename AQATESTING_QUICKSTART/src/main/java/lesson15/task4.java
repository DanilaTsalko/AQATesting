package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class task4 {
    public static void signInScript() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Переход на сайт
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.findElement(By.name("email")).sendKeys("example@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        // //button[text()='Sign in'
        driver.findElement(By.xpath("//button[text()='Sign in']")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}