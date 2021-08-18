package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_3 {
    public static void main(String[] args) throws Exception {
        // Variables
        String submitText = "Submit";
        String hrefContact = "a[href*='#/contact']";

        // startup web service
        helper_functions _helper = new helper_functions();
        WebDriver driver  = _helper.startupWebDriver();
        // Configure this to the slowest acceptable email processing time. Ive used 20 seconds
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // Navigate to contact page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefContact)));
        driver.findElement(By.cssSelector(hrefContact)).click();
        System.out.print("Navigating to contact page\n");

        // filling out mandatory fields with bad data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(submitText)));
        driver.findElement(By.id("forename")).sendKeys("     ");
        driver.findElement(By.id("forename-err"));
        System.out.print("Verifying bad forename\n");
        driver.findElement(By.id("email")).sendKeys("dummyEmail.com");
        driver.findElement(By.id("email-err"));
        System.out.print("Verifying bad email\n");
        driver.findElement(By.id("message")).sendKeys("       ");
        driver.findElement(By.id("message-err"));
        System.out.print("Verifying bad message\n");

        driver.quit();
    }
}
