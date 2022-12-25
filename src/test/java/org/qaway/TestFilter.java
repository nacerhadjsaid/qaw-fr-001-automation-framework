package org.qaway;

import org.qaway.base.CommonAPI;
import org.qaway.utility.Utility;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFilter extends CommonAPI {

    String username = Utility.decode(prop.getProperty("username"));
    String password = Utility.decode(prop.getProperty("password"));
    @Test
    public void filterFromLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login(username, password);
        Assert.assertTrue(homePage.productsHeaderIsDisplayed());
        captureScreenshot("homepage");

        homePage.selectLowerToHigherFromFilter();
        System.out.println("list of prices: "+homePage.getItemsPrice());
        double[] finalPrices = Utility.listToArrayOfDoubles(homePage.getItemsPrice());
        Assert.assertTrue(Utility.isSorted(finalPrices));
        System.out.println("items sorted success");
    }
}
