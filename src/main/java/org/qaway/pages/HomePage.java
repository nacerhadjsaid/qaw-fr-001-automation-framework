package org.qaway.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonAPI {
    Logger LOG = LogManager.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".title")
    WebElement productsHeader;
    @FindBy(css = ".product_sort_container")
    WebElement filterDropdown;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemsPrice;
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    WebElement hamburgerMenu;
    @FindBy(xpath = "//a[@id='about_sidebar_link']")
    WebElement aboutLink;
    @FindBy(xpath = "//a[@id='inventory_sidebar_link']")
    WebElement allItemsLink;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    WebElement logoutLink;
    @FindBy(xpath = "//a[@id='reset_sidebar_link']")
    WebElement resetAppStateLink;
    @FindBy(xpath = "(//div[@class='inventory_item_price']/following-sibling::button)[1]")
    WebElement item1;

    public boolean productsHeaderIsDisplayed(){
        boolean flag = isDisplayed(productsHeader);
        LOG.info("products header is displayed");
        return flag;
    }
    public void selectLowerToHigherFromFilter(){
        selectFromDropdown(filterDropdown, "Price (low to high)");
        LOG.info("price low to high select success");
    }
    public void selectFromFilter(String option){
        selectFromDropdown(filterDropdown, option);
        LOG.info(option + " select success");
    }
    public List<String> getItemsPrice(){
        List<String> prices = new ArrayList<String>();
        for (WebElement itemPrice: itemsPrice){
            prices.add(itemPrice.getText().replace("$",""));
        }
        return prices;
    }
    public boolean validateAllItemsOptionIsDisplayed(){
        LOG.info("checking all items link is displayed ...");
        return isDisplayed(allItemsLink);
    }
    public boolean validateAboutOptionIsDisplayed(){
        LOG.info("checking about link is displayed ...");
        return isDisplayed(aboutLink);
    }
    public boolean validateLogoutOptionIsDisplayed(){
        LOG.info("checking logout link is displayed ...");
        return isDisplayed(logoutLink);
    }
    public boolean validateResetAppStateOptionIsDisplayed(){
        LOG.info("checking app state link is displayed ...");
        return isDisplayed(resetAppStateLink);
    }
    public void clickOnHamburgerMenu(){
        clickOn(hamburgerMenu);
        LOG.info("click on hamburger menu success");
    }
    public void clickAboutLink(){
        clickOn(aboutLink);
        LOG.info("click on about link success");
    }
    public void addItem1ToCart(){
        clickOn(item1);
    }
    public String getItem1Color(){
        return getElementCssValue(item1);
    }
}
