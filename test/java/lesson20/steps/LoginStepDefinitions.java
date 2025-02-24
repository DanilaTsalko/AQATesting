package lesson20.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class LoginStepDefinitions {

    @Before
    public void setUp() {
        // Настройка браузера перед запуском тестов
        Configuration.browser = "chrome";  // Устанавливаем браузер для тестов
        Configuration.headless = false;    // Отключаем headless режим для визуализации браузера
        Configuration.timeout = 10000;  // Устанавливаем тайм-аут для поиска элементов
        System.out.println("Запуск браузера перед тестом");
    }

    @Given("Set up driver Selenide")
    public void setUpDriverSelenide() {
        // Настройка браузера и страницы (если необходимо)
        System.out.println("Настройка драйвера Selenide");
    }

    @Given("I open the login page")
    public void openLoginPage() {
        open("https://qa-course-01.andersenlab.com/login");
        // Дожидаемся загрузки страницы
        $("body").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        // Вводим email
        $("[name='email']").shouldBe(Condition.visible).setValue(email);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        // Вводим пароль
        $("[name='password']").shouldBe(Condition.visible).setValue(password);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        // Нажимаем кнопку логина
        $("[type='submit']").shouldBe(Condition.visible).click();
    }

    @Then("I should see the successful login message")
    public void verifyLoginSuccess() {
        // Проверяем, что элемент с текстом "QA Manual" присутствует и видим
        $(By.xpath("//div[contains(text(), 'QA Manual')]")).shouldBe(Condition.visible);
    }


    @Then("I should see an error message")
    public void verifyLoginError() {
        // Ожидание появления сообщения об ошибке
        $(By.xpath("//span[contains(text(), 'Email or password')]")).shouldBe(Condition.visible);
    }

    @When("I click the registration button")
    public void clickRegistrationButton() {
        // Используйте правильный селектор (например, XPath или CSS)
        $(By.xpath("//span[text()='Registration']")).click();
    }


    @Then("I should be redirected to the registration page")
    public void verifyRedirectionToRegistration() {
        // Проверка, что перенаправило на страницу регистрации
        $("h1").shouldHave(Condition.text("Registration"), Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        // Закрытие браузера после тестов
        System.out.println("Закрытие браузера после теста");
        closeWebDriver();
    }
}
