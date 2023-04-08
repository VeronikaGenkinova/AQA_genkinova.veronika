import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void beforeAllTests() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.adidas.ua/");
    }

    @Test
    @DisplayName("Open Adidas login page test")
    public void testOpenLoginPage() {

        String expected = "Вхід у профіль";

        webDriver.navigate().to("https://www.adidas.ua/login");
        webDriver.findElement(By.xpath("//h1[@class='login__title']"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains("login"));
        Assertions.assertEquals(webDriver.getTitle(), expected, "Login page is opened");
    }

    @Test
    @DisplayName("Login with wrong data test")
    public void testLoginWithWrongData() {

        webDriver.navigate().to("https://www.adidas.ua/login");
        WebElement fieldEmail = webDriver.findElement(By.xpath("//input[@type='text' and @class='input-field']"));
        fieldEmail.clear();
        fieldEmail.sendKeys("vrnk@ukr.net");
        WebElement fieldPassword = webDriver.findElement(By.xpath("//input[@type='password']"));
        fieldPassword.clear();
        fieldPassword.sendKeys("pS891yusw");
        webDriver.findElement(By.xpath("//div[@class='login-form__button button-main login-form__button button-main--style-dark']/button[@type='submit']")).click();
    }

    @Test
    @DisplayName("Check empty cart test")
    public void testEmptyCartPageText() {

        String expectedEmptyCartText = "Тут з’являться товари додані до кошика. Почати покупки?";

        webDriver.navigate().to("https://www.adidas.ua/cart");
        WebElement emptyCartText = webDriver.findElement(By.xpath("//p[@class='common-text empty__text']"));
        Assertions.assertTrue(emptyCartText.isDisplayed());
        Assertions.assertEquals(emptyCartText.getText(), expectedEmptyCartText, "Empty cart page contains notification");
    }

    @AfterAll
    public static void afterAllTests() {
        webDriver.quit();
    }
}