package lesson16.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class task5 {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"email", "password"})
    public void testLogin(String email, String password) throws InterruptedException {
        // Переход на сайт
        driver.get("https://qa-course-01.andersenlab.com/login");
        Thread.sleep(5000);

        // Находим поля email и password
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign in']"));

        // Ввод данных
        emailField.clear();
        emailField.sendKeys(email);

        passwordField.clear();
        passwordField.sendKeys(password);

        // Кликаем по кнопке "Sign In"
        loginButton.click();
        Thread.sleep(4000);

        // Проверяем, что логин успешен
        assertTrue(isLoginSuccessful(), "Login failed for user: " + email);
    }

    private boolean isLoginSuccessful() {
        try {
            // Проверяем наличие элемента, который появляется после успешного входа
            driver.findElement(By.xpath("//div[contains(text(), 'QA Manual')]"));
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
