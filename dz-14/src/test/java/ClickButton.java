import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class ClickButton {



    @Test
    public void testClickMeButton() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://demoqa.com/elements");

        WebElement buttonsLink = driver.findElement(By.xpath("//span[text()='Buttons']"));
        buttonsLink.click();
        Thread.sleep(500);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        Thread.sleep(500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", clickMeButton);
        Thread.sleep(500);

        WebElement message = driver.findElement(By.id("dynamicClickMessage"));
        String messageText = message.getText();

        Assert.assertEquals(messageText, "You have done a dynamic click", "Повідомлення не відповідає очікуваному.");

        Thread.sleep(5000);
        driver.quit();
    }

}
