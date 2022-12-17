package org.qaway;

import com.google.common.collect.Ordering;
import org.qaway.base.CommonAPI;
import org.qaway.base.Utility;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFilter extends CommonAPI {

    @Test
    public void filterFromLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.productsHeaderIsDisplayed());

        homePage.selectLowerToHigherFromFilter();
        System.out.println("list of prices: "+homePage.getItemsPrice());
        double[] finalPrices = Utility.listToArrayOfDoubles(homePage.getItemsPrice());
        Assert.assertTrue(Utility.isSorted(finalPrices));
        System.out.println("items sorted success");
    }
}
