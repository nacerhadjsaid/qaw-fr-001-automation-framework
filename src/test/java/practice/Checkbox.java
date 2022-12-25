package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Checkbox {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        String str1 = "1";
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@name='checkBoxOption"+str1+"']"));
        checkbox1.click();
        System.out.println(checkbox1.isSelected());
        Assert.assertTrue(checkbox1.isSelected());
        Thread.sleep(3000);

        String str2 = "2";
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@name='checkBoxOption"+str2+"']"));
        checkbox2.click();
        System.out.println(checkbox2.isSelected());
        Assert.assertTrue(checkbox2.isSelected());
        Thread.sleep(3000);

        String str3 = "3";
        WebElement checkbox3 = driver.findElement(By.xpath("//input[@name='checkBoxOption"+str3+"']"));
        System.out.println(checkbox3.isSelected());
        Assert.assertFalse(checkbox3.isSelected());
        Thread.sleep(3000);

        driver.close();

    }
}
