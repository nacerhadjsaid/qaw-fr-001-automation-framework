package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class RadioButton {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[@type='radio']"));

        for (WebElement radioButton: radioButtons) {
            if (radioButton.getAttribute("value").equalsIgnoreCase("radio3")){
                radioButton.click();
            }
        }

        Thread.sleep(3);

        driver.close();

    }

}
