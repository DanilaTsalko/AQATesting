package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class task1 {
    public static void useTestCase() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Переход на сайт
        driver.get("https://qa-course-01.andersenlab.com/login");

        //TU10 перенаправление на окно регистрации
        driver.findElement(By.xpath("//span[text() = 'Registration']")).click();
        Thread.sleep(2000);
        System.out.println("TU10 пройден успешно");

        //TU11 регистрация с корректными данными
        driver.findElement(By.name("firstName")).sendKeys("firstName");
        driver.findElement(By.name("lastName")).sendKeys("lastName");
        driver.findElement(By.name("dateOfBirth")).sendKeys("01/29/2025"+ Keys.ENTER);
        driver.findElement(By.name("email")).sendKeys("myexample@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        Thread.sleep(2000);
        System.out.println("TU11 пройден успешно");


        Thread.sleep(5000);
        driver.quit();
    }
}