package lesson18;

import io.qameta.allure.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class loginPageTest {
    private static final Logger logger = LoggerFactory.getLogger(loginPageTest.class);
    private static WebDriver driver;
    private static loginPage loginPage;

    @BeforeClass
    public static void setUpClass() {
        logger.info("Запуск тестового класса");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Before
    public void setUp() {
        logger.info("Открытие страницы логина");
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage = new loginPage(driver);
    }

    @AfterClass
    public static void tearDownClass() {
        logger.info("Закрытие браузера после тестов");
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Позитивный тест входа в систему")
    @Test
    public void loginPositive() throws InterruptedException {
        logger.info("Запуск теста: loginPositive");
        loginPage.enterEmail("example@gmail.com")
                .enterPassword("12345678")
                .clickLogin();

        Assert.assertTrue("Вход должен быть успешным", loginPage.isLoginSuccessful());
        logger.info("Тест loginPositive успешно выполнен");
    }

    @Description("Негативный тест входа с неправильным паролем")
    @Test
    public void loginNegativeWrongPassword() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeWrongPassword");
        loginPage.enterEmail("example@gmail.com")
                .enterPassword("AbraCadabra")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeWrongPassword успешно выполнен");
    }

    @Description("Негативный тест входа с неправильным email")
    @Test
    public void loginNegativeWrongEmail() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeWrongEmail");
        loginPage.enterEmail("WrongExample@mail.com")
                .enterPassword("12345678")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeWrongEmail успешно выполнен");
    }

    @Description("Негативный тест входа с пустыми полями")
    @Test
    public void loginNegativeEmptyFields() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeEmptyFields");
        loginPage.clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeEmptyFields успешно выполнен");
    }

    @Description("Негативный тест входа с пустым полем email")
    @Test
    public void loginNegativeEmptyEmailField() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeEmptyEmailField");
        loginPage.enterPassword("12345678")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeEmptyEmailField успешно выполнен");
    }

    @Description("Негативный тест входа с пустым паролем")
    @Test
    public void loginNegativeEmptyPasswordField() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeEmptyPasswordField");
        loginPage.enterEmail("example@mail.com")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeEmptyPasswordField успешно выполнен");
    }

    @Description("Негативный тест с невалидным полем email")
    @Test
    public void loginNegativeInvalidEmailField() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeInvalidEmailField");
        loginPage.enterEmail("invalidLogin")
                .enterPassword("12345678")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeInvalidEmailField успешно выполнен");
    }

    @Description("Негативный тест с паролем короче чем 8 символов")
    @Test
    public void loginNegativeShortPassword() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeShortPassword");
        loginPage.enterEmail("example@mail.com")
                .enterPassword("1234567")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeShortPassword успешно выполнен");
    }

    @Description("Негативный тест с паролем больше чем 20 символов")
    @Test
    public void loginNegativeLongPassword() throws InterruptedException {
        logger.info("Запуск теста: loginNegativeLongPassword");
        loginPage.enterEmail("example@mail.com")
                .enterPassword("12345678901234567890122")
                .clickLogin();

        Assert.assertFalse("Вход не должен быть успешным", loginPage.isLoginUnsuccessful());
        logger.info("Тест loginNegativeLongPassword успешно выполнен");
    }

    @Description("Позитивный тест перехода на страницу регистрации")
    @Test
    public void redirectionToRegistration() {
        logger.info("Запуск теста: redirectionToRegistration");
        loginPage.clickRegistration();

        Assert.assertTrue("Перенаправление должно быть успешным", loginPage.isRedirectionSuccessful());
        logger.info("Тест redirectionToRegistration успешно выполнен");
    }
}
