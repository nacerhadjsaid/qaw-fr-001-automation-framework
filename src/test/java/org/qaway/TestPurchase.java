package org.qaway;

import org.qaway.base.CommonAPI;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPurchase extends CommonAPI {

    @Test
    public void addItemToCard(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        //login
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.productsHeaderIsDisplayed());

        //add item to cart
        System.out.println(homePage.getItem1Color());
        homePage.addItem1ToCart();

        //check item gets black colored
        System.out.println(homePage.getItem1Color());
    }
}
