package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_2 {
    // Assumptions - O
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; i++){
            // Variables
            String submitText = "Submit";
            String hrefContact = "a[href*='#/contact']";
            String alertSuccessClassName = "alert-success";

            // startup web service
            helper_functions _helper = new helper_functions();
            WebDriver driver  = _helper.startupWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, 3);
            // Configure this to the slowest acceptable email processing time. Ive used 20 seconds
            WebDriverWait submitWait = new WebDriverWait(driver, 20);

            // Navigate to contact page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefContact)));
            driver.findElement(By.cssSelector(hrefContact)).click();
            System.out.print("Navigating to contact page\n");

            // Click submit with mandatory fields complete
            // Since it was mandatory only, other fields have been left untouched.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(submitText)));
            _helper.inputContactPageMandatory(driver);
            driver.findElement(By.linkText(submitText)).click();
            System.out.print("Filling mandatory fields and submitting\n");

            // Check success popup arrives
            submitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(alertSuccessClassName)));
            driver.findElement(By.className(alertSuccessClassName));
            System.out.print("Check success popup\n");
            driver.quit();
            System.out.print("quiting\n");
        }
    }
}
