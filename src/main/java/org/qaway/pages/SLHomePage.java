package org.qaway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

public class SLHomePage extends CommonAPI {

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
        System.out.println("hover over contact success");
    }
    public void clickOnSauceCommunity(){
        clickOn(sauceCommunity);
        System.out.println("click on sauce community success");
    }
}
