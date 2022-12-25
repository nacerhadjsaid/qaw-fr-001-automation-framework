package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WindowTabHandle {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.xpath("//a[@id='opentab']")).click();

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();

        String parent = it.next();
        String tab = it.next();

        driver.switchTo().window(tab);
        System.out.println("switch to tab success");

        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        Thread.sleep(3000);

        driver.quit();

    }
}
