package tests;

import models.Profile;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    @Test(description = "Open edit profile page")
    public void editProfilePageShouldBeOpened() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        profilePage.openProfilePage();
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page wasn't opened");
    }

    @Test(description = "Fill in User profile form")
    public void fillInProfileForm() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        profilePage.openProfilePage();
        Profile profile = new Profile("female", "11/12/2000",
                "55.00", "kg", "Bahrain", "Al Janubiyah", "Minsk", "220000");
        profilePage.openEditProfileForm();
        profilePage.editProfile(profile);
        profilePage.saveProfileChanges();
        profilePage.validateProfile(profile);
        profilePage.openEditProfileForm();
        profilePage.clean();
        profilePage.saveProfileChanges();
    }


//    @Test(description = "Add user photo to the profile")
//    public void profilePhotoShouldBeAdded() {
//        loginPage.open();
//        loginPage.login(USER, PASSWORD);
//        profilePage.openProfilePage();
//        profilePage.openEditProfileForm();
//        profilePage.uploadPhoto("src/test/resources/cat.jpg");
//        String fileName = driver.findElement(By.cssSelector(".fileupload-preview")).getText();
//        Assert.assertEquals(fileName, "cat.jpg", "File was not uploaded");
//    }
}
