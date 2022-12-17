package org.qaway.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonAPI {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("driver.chromedriver", "/Users/nacer-zimu/IdeaProjects/qaw-fr-001-automation/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        System.out.println("browser open success");
    }
    @AfterMethod
    public void close(){
        driver.close();
        System.out.println("browser close success");
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
    public void clickOn(WebElement element){
        element.click();
    }
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public String getWebElementText(WebElement element){
        return element.getText();
    }
    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectFromDropdown(WebElement dropdown, String option){
        Select select  = new Select(dropdown);
        try {
            select.selectByVisibleText(option);
        }catch (Exception e){
            select.selectByValue(option);
        }

    }
}
