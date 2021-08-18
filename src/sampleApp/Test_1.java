package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_1 {
    public static void main(String[] args) throws Exception {
        // Variables
        String submitText = "Submit";
        String alertErrorClassName = "alert-error";
        String alertSuccessClassName = "alert-success";
        String hrefContact = "a[href*='#/contact']";

        // startup web service
        helper_functions _helper = new helper_functions();
        WebDriver driver  = _helper.startupWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebDriverWait submitWait = new WebDriverWait(driver, 20);

        // Navigate to contact page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hrefContact)));
        driver.findElement(By.cssSelector(hrefContact)).click();
        System.out.print("Navigating to contact page\n");

        // Click submit with no fields complete
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(submitText)));
        driver.findElement(By.linkText(submitText)).click();
        System.out.print("Submitting empty form\n");

        // Verify Bad submit popup is present
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(alertErrorClassName)));
        driver.findElement(By.className(alertErrorClassName));
        System.out.print("Verifying bad popup\n");

        // Click submit with mandatory fields complete
        // Since it was mandatory only, other fields have been left untouched.
        _helper.inputContactPageMandatory(driver);
        driver.findElement(By.linkText(submitText)).click();
        System.out.print("Filling out mandatory fields\n");

        // Check success popup arrives
        submitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(alertSuccessClassName)));
        driver.findElement(By.className(alertSuccessClassName));
        System.out.print("Checking success pop up\n");
        driver.quit();
        System.out.print("quitting\n");

    }
}
