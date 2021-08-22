package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Hashtable;
import java.util.List;

public class Test_4 {
    public static void main(String[] args) throws Exception {
        // list of items to buy
        Hashtable<String, Integer> shoppingList = new Hashtable<>();
        shoppingList.put("product-2", 2);
        shoppingList.put("product-4", 5);
        shoppingList.put("product-7", 3);
        String hrefShop = "a[href*='#/shop']";
        String hrefCart = "a[href*='#/cart']";
        String hrefCheckout = "a[href*='#/checkout']";

        // startup web service
        helper_functions _helper = new helper_functions();
        WebDriver driver  = _helper.startupWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 3);

        // Navigate to shopping page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefShop)));
        driver.findElement(By.cssSelector(hrefShop)).click();
        System.out.print("Navigating to shop page\n");

        // finds/buys the products
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Buy")));
        shoppingList.forEach((k,v) -> {
            for(int i = 0; i < (int) v; i++){
                driver.findElement(By.xpath("//*[@id=\"" + k + "\"]/div/p/a")).click();
                System.out.print("Buying " + k + "\n");
            }
        });

        // Navigate to cart
        driver.findElement(By.cssSelector(hrefCart)).click();
        System.out.print("Navigating to cart\n");

        // Wait for checkout load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefCheckout)));
        // Validate cart
        boolean frogPresent = false;
        boolean bunnyPresent = false;
        boolean bearPresent = false;
        WebElement formElement = driver.findElement(By.className("ng-scope"));
        List<WebElement> list = formElement.findElements(By.xpath("*"));
        for(WebElement item:list) {
            if (item.getText().contains("Stuffed Frog $10.99 $21.98")){
                System.out.print("Frog present in cart");
                frogPresent = true;
            }
            if (item.getText().contains("Fluffy Bunny $9.99 $49.95")){
                System.out.print("Bunny present in cart");
                bunnyPresent = true;
            }
            if (item.getText().contains("Valentine Bear $14.99 $44.97")){
                System.out.print("Bear present in cart");
                bearPresent = true;
            }
        }
        if(!frogPresent || !bunnyPresent || !bearPresent){
            throw new Exception("Items not present in cart!!!!");
        }
        System.out.print("Validated cart\n");
        driver.quit();
        System.out.print("quitting\n");
    }
}
