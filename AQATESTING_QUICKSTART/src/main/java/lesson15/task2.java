package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class task2 {

    public static void openPages( ) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Сохраняем оригинальное окно
        String originalWindow = driver.getWindowHandle();

        // Ссылки на страницы
        List<String> urls = new ArrayList<>();
        urls.add("http://www.automationpractice.pl/index.php");
        urls.add("https://www.w3schools.com/");
        urls.add("https://www.clickspeedtester.com/click-counter/");
        urls.add("https://zoo.waw.pl/");
        urls.add("https://andersenlab.com/");

        for (String url : urls) {
            // Открываем новую вкладку и переходим по ссылке
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
            System.out.println("Page Title: " + driver.getTitle() + " URL: " + driver.getCurrentUrl());

            // Закрываем вкладку, если в названии содержится "zoo"
            if (driver.getTitle().toLowerCase().contains("zoo")) {
                driver.close();
                // После закрытия вкладки возвращаемся к последней открытой
                Set<String> windowHandles = driver.getWindowHandles();
                windowHandles.remove(originalWindow);
                driver.switchTo().window(windowHandles.iterator().next());
            }
        }

        // Возвращаемся к оригинальному окну
        driver.switchTo().window(originalWindow);

        driver.quit();
    }
}
