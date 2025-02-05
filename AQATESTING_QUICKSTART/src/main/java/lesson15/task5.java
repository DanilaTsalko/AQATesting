package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;

public class task5 {
    public static void PhotoScript() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Переход на сайт
        driver.get("https://qa-course-01.andersenlab.com/login");

        // Авторизация
        driver.findElement(By.name("email")).sendKeys("example@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Sign in']")).click();
        Thread.sleep(2000);

        // Кликаем на аватарку (просто открывает системный проводник, с ним через selenium работать нельзя)
        driver.findElement(By.xpath("//img[contains(@src, 'upload')]")).click();
        Thread.sleep(2000);

        // Получаем путь к файлу внутри проекта
        String filePath = getFileFromResources("images/photo.jpg");

        // Находим input для загрузки файла
        WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));

        // Передаём путь к файлу
        uploadInput.sendKeys(filePath);

        Thread.sleep(5000);
        driver.quit();
    }

    private static String getFileFromResources(String fileName) {
        URL resource = task5.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("Файл не найден: " + fileName);
        }
        try {
            return new File(resource.toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении пути к файлу: " + fileName, e);
        }
    }

}
