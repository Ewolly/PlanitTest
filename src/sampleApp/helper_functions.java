package sampleApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class helper_functions {

    public WebDriver startupWebDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
        System.out.print("Starting webdriver\n");
        return driver;
    }

    public void inputContactPageMandatory(WebDriver driver) throws Exception {
        driver.findElement(By.id("forename")).sendKeys("Hunter");
        driver.findElement(By.id("email")).sendKeys("dummyEmail@notAnEmail.com");
        driver.findElement(By.id("message")).sendKeys("Hi I would like to ask about the Cow");
    }
}
