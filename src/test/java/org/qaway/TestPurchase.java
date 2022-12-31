package org.qaway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.qaway.base.CommonAPI;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.qaway.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPurchase extends CommonAPI {
    Logger LOG = LogManager.getLogger(TestPurchase.class.getName());

    String username = Utility.decode(prop.getProperty("username"));
    String password = Utility.decode(prop.getProperty("password"));
    @Test
    public void addItemToCard(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        //login
        loginPage.login(username, password);
        Assert.assertTrue(homePage.productsHeaderIsDisplayed());

        //add item to cart
        LOG.info(homePage.getItem1Color());
        homePage.addItem1ToCart();

        //check item gets black colored
        LOG.info(homePage.getItem1Color());
    }
}
