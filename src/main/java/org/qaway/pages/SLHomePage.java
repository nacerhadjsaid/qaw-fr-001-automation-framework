package org.qaway.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

public class SLHomePage extends CommonAPI {

    Logger LOG = LogManager.getLogger(SLHomePage.class.getName());
    public SLHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//div[@class='nav-menu']//a[@class='link'][normalize-space()='Contact']")
    WebElement contact;
    @FindBy (xpath = "//div[@class='nav-menu-link-group-row-list-wrapper']//span[contains(text(),'Sauce Community')]")
    WebElement sauceCommunity;

    public String getSLHomePageUrl(WebDriver driver){
        return getPageUrl(driver);
    }
    public void hoverOverContact(WebDriver driver){
        hoverOver(driver, contact);
        LOG.info("hover over contact success");
        System.out.println();
    }
    public void clickOnSauceCommunity(){
        clickOn(sauceCommunity);
        LOG.info("click on sauce community success");
    }
}
