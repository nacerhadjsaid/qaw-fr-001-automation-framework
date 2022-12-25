package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AlertHandle {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        //browser based popup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement alertField = driver.findElement(By.xpath("//input[@id='name']"));
        WebElement alertBtb = driver.findElement(By.xpath("//input[@id='alertbtn']"));
        WebElement confirmBtn = driver.findElement(By.xpath("//input[@id='confirmbtn']"));

        alertField.sendKeys("Hanafi");
        alertBtb.click();
        Thread.sleep(3000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(3000);


        alertField.sendKeys("Hanafi");
        confirmBtn.click();
        Thread.sleep(3000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        alertField.sendKeys("Hanafi");
        confirmBtn.click();
        Thread.sleep(3000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
        Thread.sleep(3000);

        driver.close();

    }

}
