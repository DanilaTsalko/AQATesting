package lesson18;

import io.qameta.allure.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginPageTest {
    private static WebDriver driver;
    private static loginPage loginPage;

        @BeforeClass
        public static void setUpClass() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Before
        public void setUp() {
            // Загружаем страницу перед каждым тестом
            driver.get("https://qa-course-01.andersenlab.com/login"); // URL страницы логина
            loginPage = new loginPage(driver);
        }

        @AfterClass
        public static void tearDownClass() {
            // Закрываем драйвер после выполнения всех тестов
            if (driver != null) {
                driver.quit();
            }
        }

    //1
    @Description("Позитивный тест входа в систему")
    @Test
    public void loginPositive() throws InterruptedException {
        loginPage.enterEmail("example@gmail.com")
                .enterPassword("12345678")
                .clickLogin();

        Assert.assertTrue("Вход должен быть успешным", loginPage.isLoginSuccessful());
    }

    //2
    @Description("Негативный тест входа с неправильным паролем")
    @Test
    public void loginNegativeWrongPassword() throws InterruptedException {
        loginPage.enterEmail("example@gmail.com")
                .enterPassword("AbraCadabra")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //3
    @Description("Негативный тест входа с неправильным полем email")
    @Test
    public void loginNegativeWrongEmail() throws InterruptedException {
        loginPage.enterEmail("WrongExample@mail.com")
                .enterPassword("12345678")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //4
    @Description("Негативный тест входа с пустыми полями")
    @Test
    public void loginNegativeEmptyFields() throws InterruptedException {
        loginPage.clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //5
    @Description("Негативный тест входа с пустым полем email")
    @Test
    public void loginNegativeEmptyEmailField() throws InterruptedException {
        loginPage.enterPassword("12345678")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //6
    @Description("Негативный тест входа с пустым паролем")
    @Test
    public void loginNegativeEmptyPasswordField() throws InterruptedException {
        loginPage.enterEmail("example@mail.com")
        .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //7
    @Description("Негативный тест с невалидным полем email")
    @Test
    public void loginNegativeInvalidEmailField() throws InterruptedException {
        loginPage.enterEmail("invalidLogin").
                enterPassword("12345678").
                clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //8
    @Description("Негативный тест с паролем короче чем 8 символов")
    @Test
    public void loginNegativeShortPassword() throws InterruptedException {
        loginPage.enterEmail("example@mail.com").
                enterPassword("1234567").
                clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //9
    @Description("Негативный тест с паролем больше чем 20 символов")
    @Test
    public void loginNegativeLongPassword() throws InterruptedException {
        loginPage.enterEmail("example@mail.com").
                enterPassword("12345678901234567890122").
                clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
    }

    //10
    @Description("Позитивный тест перехода на страницу регистрации")
    @Test
    public void redirectionToRegistration() throws InterruptedException {
        loginPage.clickRegistration();
        Assert.assertTrue("Перенаправление должно быть успешным", loginPage.isRedirectionSuccessful());
    }
}
