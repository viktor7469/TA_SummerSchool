import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SeleniumTest1 {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://training.by/#!/Home?lang=en");
    }

    @Test(description = "Verify that user successfully logged in with valid credentials")
    public void verifyUserSuccessfullLogin() {
        WebElement signInButton = driver.findElement(By.className("header-auth__signin"));
        signInButton.click();

        WebElement mailInputField = driver.findElement(By.id("username"));
        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        mailInputField.sendKeys("ivanhorintest@gmail.com");
        WebElement continueButton = driver.findElement(By.id("kc-login-next"));
        continueButton.click();

        WebElement passwordInputField = driver.findElement(By.id("password"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        passwordInputField.sendKeys("ivanhorintestPassword");
        WebElement signInButtonOnPasswordWindow = driver.findElement(By.id("kc-login"));
        signInButtonOnPasswordWindow.click();

        WebElement userName = driver.findElement(By.className("user-info__name"));
        Assert.assertTrue(userName.isDisplayed(), "Username is NOT displayed");
    }

    @Test(description = "Verify that user not logged in with invalid password")
    public void verifyUserUnsuccessfullLogin() throws InterruptedException {

        WebElement signInButton = driver.findElement(By.className("header-auth__signin"));
        signInButton.click();

        WebElement mailInputField = driver.findElement(By.id("username"));
        mailInputField.sendKeys("mail_for_test@gmail.com");
        WebElement continueButton = driver.findElement(By.id("kc-login-next"));
        continueButton.click();

        WebElement passwordInput = driver.findElement(By.id("password"));
        new WebDriverWait(driver, 30).
                until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        passwordInput.sendKeys("invalidPassword");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        passwordInput.sendKeys("invalidPassword");
        WebElement signInButtonOnPasswordWindow = driver.findElement(By.id("kc-login"));
        signInButtonOnPasswordWindow.click();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@class='error-text']"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


