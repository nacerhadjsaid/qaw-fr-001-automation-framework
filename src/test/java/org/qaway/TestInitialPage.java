package org.qaway;

import org.qaway.base.CommonAPI;
import org.qaway.pages.LoginPage;
import org.qaway.utility.ExcelReader;
import org.qaway.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TestInitialPage extends CommonAPI {
    ExcelReader excelReader = new ExcelReader(Utility.currentDir+ File.separator+"data"+File.separator+"test-data.xlsx", "data");

    @Test
    public void validateLandingPage(){
        String expected = excelReader.getDataForGivenHeaderAndKey("key", "login page title");
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");
    }

    @Test
    public void validateLoginPageElements(){
        LoginPage loginPage = new LoginPage(driver);
        String expected = excelReader.getDataForGivenHeaderAndKey("key", "login page title");
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //username
        boolean userNameIsDisplayed = loginPage.usernameFieldIsDisplayed();
        Assert.assertTrue(userNameIsDisplayed);
        System.out.println("username field is displayed");

        //password
        boolean passwordFieldIsDisplayed = loginPage.passwordFieldIsDisplayed();
        Assert.assertTrue(passwordFieldIsDisplayed);
        System.out.println("password field is displayed");

        //login button
        boolean loginButtonIsDisplayed = loginPage.loginBtnIsDisplayed();
        Assert.assertTrue(loginButtonIsDisplayed);
        System.out.println("login button is displayed");
    }


}
