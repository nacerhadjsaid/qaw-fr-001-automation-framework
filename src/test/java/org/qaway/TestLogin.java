package org.qaway;

import org.qaway.base.CommonAPI;
import org.qaway.pages.HomePage;
import org.qaway.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends CommonAPI {

    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        loginPage.typeUsername("standard_user");
        System.out.println("enter username success");

        //enter password
        loginPage.typePassword("secret_sauce");
        System.out.println("enter password success");

        //click on login button
        loginPage.clickOnLoginButton();
        System.out.println("login button click success");

        boolean productsHeaderIsDisplayed = homePage.productsHeaderIsDisplayed();
        Assert.assertTrue(productsHeaderIsDisplayed);
        System.out.println("Products header is displayed success");
    }
    //@Test
    public void loginAttemptWithoutUsername() {
        LoginPage loginPage = new LoginPage(driver);
        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        loginPage.typeUsername("");
        System.out.println("enter username success");

        //enter password
        loginPage.typePassword("secret_sauce");
        System.out.println("enter password success");

        //click on login button
        loginPage.clickOnLoginButton();
        System.out.println("login button click success");

        String textError = loginPage.getErrorMessage();
        System.out.println("error message: "+ textError);
        Assert.assertEquals("Epic sadface: Username is required", textError);
        System.out.println("error message validation success");
    }
    //@Test
    public void loginAttemptWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        loginPage.typeUsername("standard_user");
        System.out.println("enter username success");

        //enter password
        loginPage.typePassword("");
        System.out.println("enter password success");

        //click on login button
        loginPage.clickOnLoginButton();
        System.out.println("login button click success");

        String textError = loginPage.getErrorMessage();
        System.out.println("error message: "+ textError);
        Assert.assertEquals("Epic sadface: Password is required", textError);
        System.out.println("error message validation success");
    }
}
