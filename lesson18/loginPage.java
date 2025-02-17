package lesson18;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class loginPage {
    private static final Logger logger = LoggerFactory.getLogger(loginPage.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private static WebElement emailField;

    @FindBy(name = "password")
    private static WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign in']")
    private static WebElement signinButton;

    @FindBy(xpath = "//span[text() = 'Registration']")
    private static WebElement registrationButton;

    @Step("Ввод email: {email}")
    public loginPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        logger.info("Ввод email: {}", email);
        emailField.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public loginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        logger.info("Ввод пароля");
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Нажатие на кнопку входа")
    public loginPage clickLogin() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(signinButton));
        logger.info("Нажатие на кнопку входа");
        signinButton.click();
        Thread.sleep(2000);
        return this;
    }

    @Step("Проверка успешного входа")
    boolean isLoginSuccessful() {
        try {
            // Проверяем наличие элемента, который появляется после успешного входа
            driver.findElement(By.xpath("//div[contains(text(), 'QA Manual')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка успешного входа")
    boolean isLoginUnsuccessful() {
        try {
            // Проверяем наличие элемента, который появляется после успешного входа
            driver.findElement(By.xpath("//div[contains(text(), 'QA Manual')]"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Нажатие на кнопку регистрации")
    public loginPage clickRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        logger.info("Нажатие на кнопку регистрации");
        registrationButton.click();
        return this;
    }

    @Step("Проверка успешного входа")
    boolean isRedirectionSuccessful() {
        try {
            // Проверяем наличие элемента, который появляется после успешного входа
            driver.findElement(By.xpath("//h1"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
