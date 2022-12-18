package org.qaway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

public class SLCommunityPage extends CommonAPI {

    public SLCommunityPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[normalize-space()='Our secret sauce is our people']")
    WebElement mainHeader;

    public String getMainHeaderText(){
        System.out.println("main header display success");
       return getWebElementText(mainHeader);
    }
}
