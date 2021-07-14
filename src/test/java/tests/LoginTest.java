package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Positive test for login")
    public void successfulLogIn() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page was not opened");
        loginPage.login(USER, PASSWORD);
        Assert.assertTrue(homePage.isPageOpened(), "Log in failed");
    }



    @Test(description = "Negative test for login: no user name provided")
    public void shouldNotLoginWithoutUsername() {
        loginPage
                .open()
                .login("", PASSWORD);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.takeErrorMessage(), "Please enter your e-mail address.");
    }

    @Test(description = "Negative test for login: no password provided")
    public void shouldNotLoginWithoutPassword() {
        loginPage
                .open()
                .login(USER, "");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.takeErrorMessage(), "Please enter a password.");
    }


}

