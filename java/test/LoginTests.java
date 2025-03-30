package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validuser", "ValidPass123");
        
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message not displayed");
    }

    @Test
    public void testInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invaliduser", "WrongPass");
        
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message not shown for invalid login");
    }
}