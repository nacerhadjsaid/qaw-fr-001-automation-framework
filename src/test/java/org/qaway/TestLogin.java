package org.qaway;

import org.qaway.base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends CommonAPI {

    @Test
    public void loginWithValidCredentials() {
        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        type("#user-name", "standard_user");
        System.out.println("enter username success");

        //enter password
        type("#password", "secret_sauce");
        System.out.println("enter password success");

        //click on login button
        clickOn("#login-button");
        System.out.println("login button click success");
        boolean productsHeaderIsDisplayed =isDisplayed(".title");
        Assert.assertTrue(productsHeaderIsDisplayed);
        System.out.println("Products header is displayed success");
    }
    @Test
    public void loginAttemptWithoutUsername() {
        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        type("#user-name", "");

        //enter password
        type("#password", "secret_sauce");
        System.out.println("enter password success");

        //click on login button
        clickOn("#login-button");
        System.out.println("login button click success");
        String textError = getWebElementText(".error-message-container.error");
        System.out.println("error message: "+ textError);
        Assert.assertEquals("Epic sadface: Username is required", textError);
        System.out.println("error message validation success");
    }
    @Test
    public void loginAttemptWithoutPassword() {
        //check user land on the right page
        String expected = "Swag Labs";
        String actual = getPageTitle();

        Assert.assertEquals(expected, actual);
        System.out.println("page title validation success");

        //enter username
        type("#user-name", "standard_user");
        System.out.println("enter username success");

        //enter password
        type("#password", "");

        //click on login button
        clickOn("#login-button");
        System.out.println("login button click success");
        String textError = getWebElementText(".error-message-container.error");
        System.out.println("error message: "+ textError);
        Assert.assertEquals("Epic sadface: Password is required", textError);
        System.out.println("error message validation success");
    }
}
