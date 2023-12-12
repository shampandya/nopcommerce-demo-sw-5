package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test(priority = 1, groups = {"sanity", "regression"})
    public void userShouldNavigateToLoginPageSuccessFully(){
        //Click on login link
        homePage.clickOnLoginLink();
        //verify that "Welcome, Please Sign In!" message display
        String expectedMessage = "Welcome, Please Sign In!";
        String actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(expectedMessage, actualMessage, "Login page not displayed");
    }

    @Test(priority = 2, groups = {"smoke", "regression"})
    public void verifyTheErrorMessageWithInValidCredentials(){
        //Click on login link
        homePage.clickOnLoginLink();
        //Enter EmailId
        loginPage.enterEmailId("prize123@gmail.com");
        //Enter Password
        loginPage.enterPassword("prize123");
        //Click on Login Button
        loginPage.clickOnLoginButton();
        //Verify that the Error message
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found";
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message not displayed");
    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials() throws InterruptedException {
        //Click on login link
        homePage.clickOnLoginLink();
        // enter email id
        loginPage.enterEmailId("sara@gmail.com");
        // enter password
        loginPage.enterPassword("123456789@sara");
        Thread.sleep(3000);
        //click on login button
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        //Verify that LogOut link is display
        Assert.assertTrue(homePage.isLogOutLinkDisplay(), "Logout link is not displayed");
    }


    @Test(priority = 4, groups = {"regression"})
    public void verifyThatUserShouldLogOutSuccessFully() throws InterruptedException {
        //Click on login link
        homePage.clickOnLoginLink();
        //Enter EmailId
        loginPage.enterEmailId("sara@gmail.com");
        //Enter Password
        loginPage.enterPassword("123456789@sara");
        Thread.sleep(3000);
        //Click on Login Button
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        //Click on LogOut Link
        homePage = homePage.clickOnLogOutLink();
        //Verify that LogIn Link Display
        Assert.assertTrue(homePage.isLogInLinkDisplay(), "Login link is not displayed");
    }
}

