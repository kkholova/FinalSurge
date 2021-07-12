package tests;

import models.Profile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    @Test
    public void openProfileEditPage() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        profilePage.openProfilePage();
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page wasn't opened");
    }

    @Test
    public void fillInProfileForm() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        profilePage.openProfilePage();
        Profile profile = new Profile("female", "11/12/2000",
                "55.00", "kg", "Belarus", "Minskaya voblasts", "Minsk", "220000");
        profilePage.openEditProfileForm();
        profilePage.editProfile(profile);
        profilePage.saveProfileChanges();
        profilePage.validateProfile(profile);
    }
}
