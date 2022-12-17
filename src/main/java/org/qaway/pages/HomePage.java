package org.qaway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonAPI {

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".title")
    WebElement productsHeader;
    @FindBy(css = ".product_sort_container")
    WebElement filterDropdown;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemsPrice;

    public boolean productsHeaderIsDisplayed(){
        System.out.println("products header is displayed");
        return isDisplayed(productsHeader);
    }
    public void selectLowerToHigherFromFilter(){
        selectFromDropdown(filterDropdown, "Price (low to high)");
        System.out.println("price low to high select success");
    }
    public void selectFromFilter(String option){
        selectFromDropdown(filterDropdown, option);
        System.out.println(option + " select success");
    }
    public List<String> getItemsPrice(){
        List<String> prices = new ArrayList<String>();
        for (WebElement itemPrice: itemsPrice){
            prices.add(itemPrice.getText().replace("$",""));
        }
        return prices;
    }
}
