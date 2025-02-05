package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class task3 {
    public static void compareElements() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Переход на сайт
        driver.get("https://www.w3schools.com/");

        // Находим элементы
        //WebElement element1 = driver.findElement(By.id("learntocode_searchbtn"));
        //WebElement element2 = driver.findElement(By.id("search2"));

        //WebElement element1 = driver.findElement(By.id("search2"));
        //WebElement element2 = driver.findElement(By.id("search2"));

        WebElement element1 = driver.findElement(By.id("pagetop"));
        WebElement element2 = driver.findElement(By.id("search2"));

        // Получаем координаты элементов
        Point location1 = element1.getLocation();
        Point location2 = element2.getLocation();

        // Определяем, какой элемент выше
        if (location1.getY() < location2.getY()) {
            System.out.println("Первый элемент находится выше второго.");
        } else if (location1.getY() > location2.getY()) {
            System.out.println("Второй элемент находится выше первого.");
        } else {
            System.out.println("Оба элемента находятся на одной высоте.");
        }

        // Определяем, какой элемент левее
        if (location1.getX() < location2.getX()) {
            System.out.println("Первый элемент находится левее второго.");
        } else if (location1.getX() > location2.getX()) {
            System.out.println("Второй элемент находится левее первого.");
        } else {
            System.out.println("Оба элемента находятся на одной вертикальной линии.");
        }

        // Получаем размеры элементов
        Dimension size1 = element1.getSize();
        Dimension size2 = element2.getSize();

        int area1 = size1.getWidth() * size1.getHeight();
        int area2 = size2.getWidth() * size2.getHeight();

        // Определяем, какой элемент занимает большую площадь
        if (area1 > area2) {
            System.out.println("Первый элемент занимает большую площадь.");
        } else if (area1 < area2) {
            System.out.println("Второй элемент занимает большую площадь.");
        } else {
            System.out.println("Оба элемента занимают одинаковую площадь.");
        }
        driver.quit();
    }
}