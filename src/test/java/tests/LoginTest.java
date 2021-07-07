package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Positive test for login")
    public void login(){
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(),"Login page was not opened");
        loginPage.login(USER,PASSWORD);
        Assert.assertTrue(homePage.isPageOpened(),"Log in failed");

    }


}
