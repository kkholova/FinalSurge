package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Positive test for login")
    public void successfulLogIn() {
        loginPage.open(baseUrl);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page was not opened");
        loginPage.login(email,password);
        Assert.assertTrue(homePage.isPageOpened(), "Log in failed");
    }



    @Test(description = "Negative test for login: no user name provided")
    public void shouldNotLoginWithoutUsername() {
        loginPage
                .open(baseUrl)
                .login("", password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.takeErrorMessage(), "Please enter your e-mail address.");
    }

    @Test(description = "Negative test for login: no password provided")
    public void shouldNotLoginWithoutPassword() {
        loginPage
                .open(baseUrl)
                .login(email, "");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.takeErrorMessage(), "Please enter a password.");
    }


}

