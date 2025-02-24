package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class task4 {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @DataProvider(name = "userCredentials")
    public Object[][] getUsers() {
        return new String[][]{
                {"1lesson16@gmail.com", "12345678"},
                {"2lesson16@gmail.com", "12345678"},
                {"3lesson16@gmail.com", "12345678"},
        };
    }

    @Test(dataProvider = "userCredentials")
    public void testLogin(String email, String password) throws InterruptedException {
        setup();
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
        // Проверяем, что логин успешен (например, проверяя URL или появление элемента)
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
