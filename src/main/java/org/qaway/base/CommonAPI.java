package org.qaway.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class CommonAPI {
    String username = "nacerhadjsaid1";
    String password = "pK4miZ8sp15afqsvGckE";

    public WebDriver driver;

    public WebDriver getDriver(String os, String browserName){
        if(os.equalsIgnoreCase("OS X")){
            if (browserName.equalsIgnoreCase("chrome")){
                System.setProperty("driver.chromedriver", "../drivers/mac/chromedriver");
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("driver.geckodriver", "../drivers/mac/geckodriver");
                driver = new FirefoxDriver();
            }
        } else if (os.equalsIgnoreCase("windows")) {
            if (browserName.equalsIgnoreCase("chrome")){
                System.setProperty("driver.chromedriver", "..\\driver\\win\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("driver.geckodriver", "..\\drivers\\win\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } else if (os.equalsIgnoreCase("linux")) {
            if (browserName.equalsIgnoreCase("chrome")){
                System.setProperty("driver.chromedriver", "../drivers/mac/chromedriver");
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("driver.geckodriver", "../drivers/mac/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }

    public WebDriver getCLoudDriver(String envName, String os, String osVersion, String browserName, String browserVersion, String username, String password) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", osVersion);
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        if (envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"),cap);
        } else if (envName.equalsIgnoreCase("saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd/hub"),cap);
        }
        return driver;
    }

    @Parameters({"useCloudEnv","envName","url","os","osVersion","browserName","browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv,@Optional("browserstack") String envName,
                      @Optional("https://www.google.com") String url,@Optional("OS X") String os,
                      @Optional("Ventura") String osVersion,@Optional("chrome") String browserName,
                      @Optional("108") String browserVersion) throws MalformedURLException
    {
        if (useCloudEnv){
            getCLoudDriver(envName,os,osVersion,browserName,browserVersion,username,password);
        }else {
            getDriver(os, browserName);
        }

        driver.manage().window().maximize();
        driver.get(url);
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
    public String getPageUrl(WebDriver driver1){
        return driver1.getCurrentUrl();
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
    public void hoverOver(WebDriver driver1, WebElement element){
        Actions actions = new Actions(driver1);
        actions.moveToElement(element).build().perform();
    }
    public String getElementCssValue(WebElement element){
        return element.getCssValue("color");
    }
}
