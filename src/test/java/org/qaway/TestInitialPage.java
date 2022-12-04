package org.qaway;

import org.qaway.base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestInitialPage extends CommonAPI {

    @Test
    public void validateLandingPage(){
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");
    }

    @Test
    public void validateLoginPageElements(){
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //username
        boolean userNameIsDisplayed = isDisplayed("#user-name");
        Assert.assertTrue(userNameIsDisplayed);
        System.out.println("username field is displayed");

        //password
        boolean passwordFieldIsDisplayed = isDisplayed("#password");
        Assert.assertTrue(passwordFieldIsDisplayed);
        System.out.println("password field is displayed");

        //login button
        boolean loginButtonIsDisplayed =isDisplayed("#login-button");
        Assert.assertTrue(loginButtonIsDisplayed);
        System.out.println("login button is displayed");
    }


}
