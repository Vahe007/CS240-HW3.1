import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeworkTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSeleniumScript() {
        navigateToHomePage();
        navigateToAndroidCategory();
        addAndroidProductToBasket();
        navigateToHtmlCategory();
        addHtmlProductToBasket();
        verifyBasket();
        removeProductFromBasket();
        driver.navigate().refresh();
        assertEquals("3 Items", driver.findElement(By.className("cartcontents")).getText());
    }

    private void navigateToHomePage() {
        driver.get("https://practice.automationtesting.in/");
        waitForElementVisibility(By.cssSelector("#header"));
        assertTrue(driver.findElement(By.cssSelector("#header")).isDisplayed());
    }

    private void navigateToAndroidCategory() {
        WebElement shopHeader = driver.findElement(By.id("menu-item-40"));
        shopHeader.click();
        WebElement androidLink = driver.findElement(By.xpath("//a[@href='https://practice.automationtesting.in/product-category/android/']"));
        assertTrue(androidLink.isDisplayed());
        androidLink.click();
    }

    private void addAndroidProductToBasket() {
        WebElement addBasketAndroid = waitForElementVisibility(By.xpath("//a[@href='/product-category/android/?add-to-cart=169']"));
        assertTrue(addBasketAndroid.isDisplayed());
        addBasketAndroid.click();
        sleepInSeconds(1);
    }

    private void navigateToHtmlCategory() {
        driver.navigate().back();
        WebElement htmlLink = driver.findElement(By.xpath("//a[@href='https://practice.automationtesting.in/product-category/html/']"));
        assertTrue(htmlLink.isDisplayed());
        assertTrue(htmlLink.isEnabled());
        htmlLink.click();
    }

    private void addHtmlProductToBasket() {
        List<WebElement> addBasketHtmls = driver.findElements(By.className("add_to_cart_button"));
        assertEquals(3, addBasketHtmls.size());
        for (WebElement btn : addBasketHtmls) {
            btn.click();
            sleepInSeconds(1);
        }
    }

    private void verifyBasket() {
        WebElement basketLink = driver.findElement(By.id("wpmenucartli"));
        assertTrue(basketLink.isDisplayed());
        assertEquals("4 Items", basketLink.findElement(By.className("cartcontents")).getText());
        basketLink.click();
        assertEquals(4, driver.findElements(By.className("cart_item")).size());
    }

    private void removeProductFromBasket() {
        WebElement removeItem = driver.findElement(By.className("remove"));
        removeItem.click();
        sleepInSeconds(1);
        assertEquals(3, driver.findElements(By.className("cart_item")).size());
    }


    private WebElement waitForElementVisibility(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void sleepInSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
