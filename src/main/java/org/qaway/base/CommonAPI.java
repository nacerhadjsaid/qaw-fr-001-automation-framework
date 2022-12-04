package org.qaway.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonAPI {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //System.setProperty("driver.chromedriver", "/Users/nacer-zimu/IdeaProjects/qaw-fr-001-automation/driver/chromedriver");
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
    public void clickOn(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e) {
            driver.findElement(By.xpath(locator)).click();
        }
    }
    public void type(String locator, String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e) {
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }
    }
    public boolean isDisplayed(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).isDisplayed();
        }catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isDisplayed();
        }
    }
    public String getWebElementText(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        }catch (Exception e) {
            return driver.findElement(By.xpath(locator)).getText();
        }
    }
    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
