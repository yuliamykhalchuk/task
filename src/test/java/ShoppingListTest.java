
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingListTest {
    @Test
    public void verifyShoppingListA(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yulia.mykhalchuk\\IdeaProjects\\untitled\\src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        WebElement computersLink = driver.findElement(By.linkText("Computers"));
        computersLink.click();
        WebElement desktopsLink = driver.findElement(By.linkText("Desktops"));
        desktopsLink.click();
        Select options = new Select(driver.findElement(By.id("products-pagesize")));
        options.selectByVisibleText("4");
        //or options.selectByIndex(0);
        WebElement selectedNumber = driver.findElement(By.name("products-pagesize"));
        Boolean checkOption = selectedNumber.isDisplayed();
        if (checkOption) {
            System.out.println("The only 4 items displayed.");
        } else {
            System.out.println("Nothing displayed.");
        }
        Select sorting = new Select(driver.findElement(By.id("products-orderby")));
        sorting.selectByVisibleText("Price: High to Low");
        WebElement cart = driver.findElement(By.cssSelector("input[value =\"Add to cart\"]"));
        cart.click();
        WebElement shoppingCartLink = driver.findElement(By.id("add-to-cart-button-74"));
        shoppingCartLink.click();
        WebElement shoppingCart = driver.findElement(By.className("product-name"));
        Boolean checkCartOption = shoppingCart.isDisplayed();
        if(checkCartOption){
            System.out.println("The item is in the shopping cart.");
        }else {
            System.out.println("Your Shopping Cart is empty.");
        }
    };
    @Test
    public void verifyShoppingListB() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yulia.mykhalchuk\\IdeaProjects\\untitled\\src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driverB = new ChromeDriver();
        driverB.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverB.manage().window().maximize();
        driverB.get("http://demowebshop.tricentis.com/build-your-own-expensive-computer-2");
        WebElement kindOfProcessor = driverB.findElement(By.cssSelector("input[value='82']"));
        kindOfProcessor.click();
        WebElement kindOfRAM = driverB.findElement(By.cssSelector("input[value='85']"));
        kindOfRAM.click();
        List<WebElement> listItems = driverB.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement item : listItems) {
                item.click();
        }
        WebElement cart = driverB.findElement(By.id("add-to-cart-button-74"));
        cart.click();
        WebElement shoppingCartLink = driverB.findElement(By.linkText("Shopping cart"));
        shoppingCartLink.click();
        WebElement shoppingCart = driverB.findElement(By.className("product-name"));
        Boolean checkOption = shoppingCart.isDisplayed();
        if(checkOption){
            System.out.println("Your shopping cart has +1 item");
        }else {
            System.out.println("Your Shopping Cart is empty.");
        }
        WebElement currentComputer = driverB.findElement(By.linkText("Build your own expensive computer"));
        currentComputer.click();
        WebElement price = driverB.findElement(By.className("price-value-74"));
        WebElement shoppingCartLink2 = driverB.findElement(By.linkText("Shopping cart"));
        shoppingCartLink2.click();
        WebElement priceInCart = driverB.findElement(By.className("product-subtotal"));
        if(priceInCart.equals(price)){
            System.out.println("The price is correct.");
        }else {
            System.out.println("Please, check your price!.");
        }
        WebElement itemInCart = driverB.findElement(By.name("removefromcart"));
        itemInCart.click();
        WebElement cleanTheCart = driverB.findElement(By.name("updatecart"));
        cleanTheCart.click();
    }
}
