package org.qaway.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qaway.base.CommonAPI;

public class LoginPage extends CommonAPI {
    Logger LOG = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    //objects
    @FindBy(css = "#user-name")
    WebElement usernameField;
    @FindBy(css = "#password")
    WebElement passwordField;
    @FindBy(css = "#login-button")
    WebElement loginBtn;
    @FindBy(css = ".error-message-container.error")
    WebElement errorMessage;

    //reusable steps
    public boolean usernameFieldIsDisplayed(){
        LOG.info("checking username field is displayed ...");
        return isDisplayed(usernameField);
    }
    public void typeUsername(String username){
        type(usernameField, username);
    }
    public boolean passwordFieldIsDisplayed(){
        LOG.info("checking password field is displayed ...");
        return isDisplayed(passwordField);
    }
    public void typePassword(String password){
        type(passwordField, password);
    }
    public boolean loginBtnIsDisplayed(){
        LOG.info("checking login button is displayed ...");
        return isDisplayed(loginBtn);
    }
    public void clickOnLoginButton(){
        clickOn(loginBtn);
    }
    public String getErrorMessage(){
        return getWebElementText(errorMessage);
    }
    public void login(String username, String password){
        typeUsername(username);
        typePassword(password);
        clickOnLoginButton();
        LOG.info("login process success");
    }
}
