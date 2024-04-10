import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomeworkTest {
    private WebDriver driver;

    @Test
    public void testSeleniumScript() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://practice.automationtesting.in/");

        driver.manage().window().maximize();

        FluentWait<WebDriver> webDriverWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#header")));

        WebElement header = driver.findElement(By.cssSelector("#header"));
        assertTrue(header.isDisplayed());

        WebElement shopHeader = driver.findElement(By.id("menu-item-40"));

        shopHeader.click();

        WebElement androidLink = driver.findElement(By.xpath("//a[@href='https://practice.automationtesting.in/product-category/android/']"));
        assertTrue(androidLink.isDisplayed());

        androidLink.click();

        WebElement addBasketAndroid = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/product-category/android/?add-to-cart=169']")));
        assertTrue(addBasketAndroid.isDisplayed());
        addBasketAndroid.click();
        TimeUnit.SECONDS.sleep(1L);


        driver.navigate().back();

        WebElement htmlLink = driver.findElement(By.xpath("//a[@href='https://practice.automationtesting.in/product-category/html/']"));
        assertTrue(htmlLink.isDisplayed());
        assertTrue(htmlLink.isEnabled());
        htmlLink.click();

        List<WebElement> addBasketHtmls = driver.findElements(By.className("add_to_cart_button"));
        assertEquals(addBasketHtmls.size(), 3);

        for (WebElement btn : addBasketHtmls) {
            btn.click();
            TimeUnit.SECONDS.sleep(1L);
        }

        WebElement basketLink = driver.findElement(By.id("wpmenucartli"));
        assertTrue(basketLink.isDisplayed());
        assertEquals(basketLink.findElement(By.className("cartcontents")).getText(), "4 Items");

        basketLink.click();

        assertEquals(driver.findElements(By.className("cart_item")).size(), 4);

        WebElement removeItem = driver.findElement(By.className("remove"));

        removeItem.click();
        TimeUnit.SECONDS.sleep(1L);
        assertEquals(driver.findElements(By.className("cart_item")).size(), 3);


        driver.navigate().refresh();
        assertEquals(driver.findElement(By.className("cartcontents")).getText(), "3 Items");

    }

//    @Test
//    public void testNavigation() {
//         driver = new ChromeDriver();
//        driver.get("https://demo.competethemes.com/");
//
//        List<WebElement> menuItems = driver.findElements(By.cssSelector(".primary-menu > li > a"));
//        assertEquals(5, menuItems.size()); // Assuming there are 5 main menu items
//        for (WebElement menuItem : menuItems) {
//            assertTrue(menuItem.isDisplayed());
//        }
//
//        List<WebElement> footerLinks = driver.findElements(By.cssSelector(".site-footer a"));
//        assertTrue(footerLinks.size() > 0);
//        for (WebElement link : footerLinks) {
//            assertTrue(link.isDisplayed());
//        }
//    }

//    @Test
//    public void testSearchFunctionality() {
//        // Open the website
//        driver.get("https://demo.competethemes.com/");
//
//        // Test search functionality
//        WebElement searchBox = driver.findElement(By.cssSelector("input.search-field"));
//        searchBox.sendKeys("blog"); // Example search term
//        searchBox.submit();
//        WebElement searchResultsTitle = driver.findElement(By.cssSelector(".page-title"));
//        assertTrue(searchResultsTitle.isDisplayed());
//        assertTrue(searchResultsTitle.getText().contains("Search Results"));
//    }
}


