package org.qaway;

import org.qaway.base.CommonAPI;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.qaway.pages.SLCommunityPage;
import org.qaway.pages.SLHomePage;
import org.qaway.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMenu extends CommonAPI {

    String username = Utility.decode(prop.getProperty("username"));
    String password = Utility.decode(prop.getProperty("password"));
    @Test
    public void about(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        SLHomePage slHomePage = new SLHomePage(driver);
        SLCommunityPage slCommunityPage = new SLCommunityPage(driver);

        //login
        loginPage.login(username, password);
        Assert.assertTrue(homePage.productsHeaderIsDisplayed());

        //click on hamburger menu
        homePage.clickOnHamburgerMenu();

        //validate hamburger menu options
        Assert.assertTrue(homePage.validateAllItemsOptionIsDisplayed());
        Assert.assertTrue(homePage.validateAboutOptionIsDisplayed());
        Assert.assertTrue(homePage.validateLogoutOptionIsDisplayed());
        Assert.assertTrue(homePage.validateResetAppStateOptionIsDisplayed());

        //click on about
        homePage.clickAboutLink();

        //click on saucelab community
        System.out.println("current url: " + slHomePage.getSLHomePageUrl(driver));
        Assert.assertEquals("https://saucelabs.com/", slHomePage.getSLHomePageUrl(driver));
        System.out.println("url validation success");
        slHomePage.hoverOverContact(driver);
        slHomePage.clickOnSauceCommunity();

        //validate user land on community page
        Assert.assertEquals("Our secret sauce is our people", slCommunityPage.getMainHeaderText());
    }
}
