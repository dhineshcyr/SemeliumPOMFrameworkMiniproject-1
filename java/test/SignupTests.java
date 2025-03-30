package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SignupPage;

public class SignupTests extends BaseTest {

    @Test
    public void testSuccessfulSignup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUp();
        
        SignupPage signupPage = new SignupPage(driver);
        signupPage.enterCredentials("testuser_" + System.currentTimeMillis(), "Test@123");
        signupPage.submitSignup();
        
        Assert.assertTrue(homePage.isSignupSuccessAlertDisplayed(), "Signup success alert not shown");
    }

    @Test
    public void testExistingUserSignup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUp();
        
        SignupPage signupPage = new SignupPage(driver);
        signupPage.enterCredentials("existinguser", "Test@123");
        signupPage.submitSignup();
        
        Assert.assertTrue(signupPage.isErrorDisplayed(), "Existing user error not displayed");
    }
}
