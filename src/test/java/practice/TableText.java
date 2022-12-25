package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TableText {
    static WebDriver driver;
    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement element = driver.findElement(By.xpath("(//table[@id='product']/tbody/tr[4]/td[2])[1]"));
        System.out.println(element.getText());

        driver.close();

    }
}
