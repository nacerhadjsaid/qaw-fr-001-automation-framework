package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SuggestionDropdown {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement element = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        element.sendKeys("can");
        Thread.sleep(2000);
        element.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);

        Thread.sleep(3000);

        driver.close();

    }
}
