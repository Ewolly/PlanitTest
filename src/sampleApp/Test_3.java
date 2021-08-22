package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class Test_3 {
    public static void main(String[] args) throws Exception {
        // list of items to buy
        String[] shoppingList = {"product-6", "product-6", "product-4"};
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
        for (String item : shoppingList){
            driver.findElement(By.xpath("//*[@id=\"" + item + "\"]/div/p/a")).click();
            System.out.print("Buying " + item + "\n");
        }

        // Navigate to cart
        driver.findElement(By.cssSelector(hrefCart)).click();
        System.out.print("Navigating to cart\n");

        // Wait for checkout load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefCheckout)));
        // Validate cart
        boolean cowPresent = false;
        boolean bunnyPresent = false;
        WebElement formElement = driver.findElement(By.className("ng-scope"));
        List<WebElement> list = formElement.findElements(By.xpath("*"));
        // TODO check this properly as this isnt dynamic
        for(WebElement item:list) {
            if (item.getText().contains("Funny Cow $10.99 $21.98")){
                cowPresent = true;
            }
            if (item.getText().contains("Fluffy Bunny $9.99 $9.99")){
                bunnyPresent = true;
            }
        }
        if(!cowPresent || !bunnyPresent){
            throw new Exception("Items not present in cart!!!!");
        }
        System.out.print("Validated cart\n");
        driver.quit();
        System.out.print("quitting\n");
    }
}
