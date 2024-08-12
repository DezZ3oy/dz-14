import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebTablesTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testAddAndEditRecord() throws InterruptedException {
        driver.get("https://demoqa.com/webtables");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
        addButton.click();

        driver.findElement(By.id("firstName")).sendKeys("Sarah");
        Thread.sleep(500);
        driver.findElement(By.id("lastName")).sendKeys("Konor");
        Thread.sleep(500);
        driver.findElement(By.id("userEmail")).sendKeys("konottrue@gambit.com");
        Thread.sleep(500);
        driver.findElement(By.id("age")).sendKeys("30");
        Thread.sleep(500);
        driver.findElement(By.id("salary")).sendKeys("50000");
        Thread.sleep(500);
        driver.findElement(By.id("department")).sendKeys("HR");
        Thread.sleep(500);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement nameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rt-tbody']//div[@role='row']//div[1][text()='Sarah']")));
        Assert.assertNotNull(nameCell, "Record was not added.");

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-record-1")));
        editButton.click();

        WebElement salaryField = driver.findElement(By.id("salary"));
        salaryField.clear();
        Thread.sleep(500);
        salaryField.sendKeys("60000");
        Thread.sleep(500);

        WebElement updateButton = driver.findElement(By.id("submit"));
        updateButton.click();

        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
