package org.qaway.base;

import org.apache.commons.io.FileUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qaway.reporting.ExtentManager;
import org.qaway.reporting.ExtentTestManager;
import org.qaway.utility.Utility;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    Logger LOG = LogManager.getLogger(CommonAPI.class.getName());
    public Properties prop = Utility.loadProperties();
    String username = prop.getProperty("bs.username");
    String password = prop.getProperty("bs.password");

    String implicitWait = prop.getProperty("implicit.wait", "10");
    String takeScreenshot = prop.getProperty("take.screenshot", "false");
    String windowMaximize = prop.getProperty("window.maximize", "true");

    public WebDriver driver;

    //extend report: 39 - 97
    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (takeScreenshot.equalsIgnoreCase("true")) {
            LOG.info("take screenshot for failure true");
            if (result.getStatus() == ITestResult.FAILURE) {
                LOG.info("test failed");
                takeScreenshot(result.getName());
                LOG.info("take screenshot");
            }
        }
        driver.quit();
        LOG.info("browser close success");
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public void getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
    }

    public void getCLoudDriver(String envName, String os, String osVersion, String browserName, String browserVersion, String username, String password) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", osVersion);
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        if (envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + password + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + password + "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }
    }

    @Parameters({"useCloudEnv", "envName", "url", "os", "osVersion", "browserName", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String envName,
                      @Optional("https://www.google.com") String url, @Optional("OS X") String os,
                      @Optional("Ventura") String osVersion, @Optional("chrome") String browserName,
                      @Optional("108") String browserVersion) throws MalformedURLException {
        if (useCloudEnv) {
            getCLoudDriver(envName, os, osVersion, browserName, browserVersion, username, password);
        } else {
            getDriver(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
        if (windowMaximize.equalsIgnoreCase("true")) {
            driver.manage().window().maximize();
        }
        driver.get(url);
        LOG.info("browser open success");
    }
//    @AfterMethod
//    public void close(){
//        driver.quit();
//        LOG.info("browser close success");
//    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver1) {
        return driver1.getCurrentUrl();
    }

    public void clickOn(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public String getWebElementText(WebElement element) {
        return element.getText();
    }

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectFromDropdown(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(option);
        } catch (Exception e) {
            select.selectByValue(option);
        }

    }

    public void hoverOver(WebDriver driver1, WebElement element) {
        Actions actions = new Actions(driver1);
        actions.moveToElement(element).build().perform();
    }

    public String getElementCssValue(WebElement element) {
        return element.getCssValue("color");
    }

    public void waitForElementToBeAvailable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void captureScreenshot(String screenshot) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("screenshots" + File.separator + screenshot + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeScreenshot(String screenshotName) {
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(Utility.currentDir + File.separator + "screenshots" + File.separator + screenshotName + " " + df.format(date) + ".png"));
            //LOG.info("Screenshot captured");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //LOG.info("Screenshot captured");
        //LOG.info("Exception while taking screenshot "+e.getMessage());
    }
}
