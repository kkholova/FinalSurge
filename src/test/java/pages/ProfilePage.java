package pages;

import elements.Inputs;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProfilePage extends BasePage {
    public static final By BREADCRUMB = By.xpath("//a[contains( text(),'User Profile')]");
    public static final By SETTINGS_BUTTON = By.xpath("//a[contains( text(),'Settings')]");
    public static final By EDIT_PROFILE = By.xpath("//span[contains(text(),'Edit Profile')]");
    public static final By SAVE_EDIT_PROFILE = By.id("saveButtonProfile");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check that Profile page is opened")
    public boolean isPageOpened() {
        log.info("Check that Profile page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }

    public void openProfilePage() {
        driver.findElement(SETTINGS_BUTTON).click();
    }

    public void openEditProfileForm() {
        driver.findElement(EDIT_PROFILE).click();
    }

//TODO add swich case for gender and weightMeasure

    public String chooseGender(String genderType) {
        switch (genderType) {
            case "female":
                genderType = "female";
                break;
            case "Public":
                genderType = "male";
                break;
        }
        return genderType;
    }

    public String chooseWeightMeasure(String weightMeasure) {
        switch (weightMeasure) {
            case "lbs":
                weightMeasure = "optionsRadios3";
                break;
            case "kg":
                weightMeasure = "optionsRadios4";
                break;
        }
        return weightMeasure;
    }

    @Step("Edit User profile")
    public void editProfile(Profile profile) {
        String gender = profile.getGender();
        String weightMeasure = profile.getWeightMeasure();
        log.info("Creating new account");
//        log.info("Add photo to profile");
//        new Inputs(driver, "UserThumbnail").uploadPhoto(profile.getPhoto());
        log.info("Set gender in profile");
        new Inputs(driver, chooseGender(gender)).tickCheckbox(chooseGender(gender));
        log.info("Add Bday to profile");
        new Inputs(driver, "BDay").writeText(profile.getBirthday());
        log.info("Add weight to profile");
        new Inputs(driver, "Weight").writeText(profile.getWeight());
        log.info("Choose weight measure in profile");
        new Inputs(driver, chooseWeightMeasure(weightMeasure)).tickCheckbox(chooseWeightMeasure(weightMeasure));
        log.info("Add country to profile");
        new Inputs(driver, "Country").selectFromDropdown(profile.getCountry());
        log.info("Add region to profile");
        new Inputs(driver, "Region").selectFromDropdown(profile.getRegion());
        log.info("Add city to profile");
        new Inputs(driver, "City").writeText(profile.getCity());
        log.info("Add zip to profile");
        new Inputs(driver, "Zip").writeText(profile.getZip());
    }

    @Step("Save profile's changes")
    public void saveProfileChanges() {
        log.info("Click on save edit profile changes");
        driver.findElement(SAVE_EDIT_PROFILE).click();
    }

    @Step("Validate data on contact's page")
    public void validateProfile (Profile profile){
        log.info("Validating validate data on account page");
        validateInput("Gender:", String.format("Gender: " +profile.getGender().substring(0,1).toUpperCase()+ profile.getGender().substring(1).toLowerCase()));
        validateInput("Birthday:", String.format("Birthday: " +profile.getBirthday()));
        validateInput("Weight:", String.format("Weight: %s %s", profile.getWeight(),profile.getWeightMeasure()));
        validateInput("Country:", String.format("Country: " + profile.getCountry()));
        validateInput("State:", String.format("State: " +profile.getRegion()));
        validateInput("City:", String.format("City: " + profile.getCity()));
        validateInput("Zip/Postal Code:",String.format("Zip/Postal Code: " +profile.getZip()));
    }

}
