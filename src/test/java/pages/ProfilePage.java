package pages;

import elements.InputHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

@Log4j2
public class ProfilePage extends BasePage {
    public static final By BREADCRUMB = By.xpath("//a[contains( text(),'User Profile')]");
    public static final By SETTINGS_BUTTON = By.xpath("//a[contains( text(),'Settings')]");
    public static final By EDIT_PROFILE = By.xpath("//span[contains(text(),'Edit Profile')]");
    public static final By SAVE_EDIT_PROFILE = By.id("saveButtonProfile");
    public static final By DELETE_PHOTO_BUTTON = By.id("del-pic");
    public static final By SUBMIT_DELETE_PHOTO = By.xpath("//div[@class = 'modal-footer']//a[contains(text(),'OK')]");
    String bdayId = "BDay";
    String weightId = "Weight";
    String cityId = "City";
    String zipId = "Zip";

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


    public String chooseGender(String genderType) {
        switch (genderType) {
            case "female":
                genderType = "female";
                break;
            case "male":
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
        log.info("Set gender in profile");
        new InputHelper(driver, chooseGender(gender)).tickRadioButton(chooseGender(gender));
        log.info("Add Bday to profile");
        new InputHelper(driver, bdayId).writeText(profile.getBirthday());
        log.info("Add weight to profile");
        new InputHelper(driver, weightId).writeText(profile.getWeight());
        log.info("Choose weight measure in profile");
        new InputHelper(driver, chooseWeightMeasure(weightMeasure)).tickRadioButton(chooseWeightMeasure(weightMeasure));
        log.info("Add country to profile");
        new InputHelper(driver, "Country").selectFromDropdown(profile.getCountry());
        log.info("Add region to profile");
        new InputHelper(driver, "Region").selectFromDropdown(profile.getRegion());
        log.info("Add city to profile");
        new InputHelper(driver, cityId).writeText(profile.getCity());
        log.info("Add zip to profile");
        new InputHelper(driver, zipId).writeText(profile.getZip());
    }

    @Step("Save profile's changes")
    public void saveProfileChanges() {
        log.info("Click on save edit profile changes");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SAVE_EDIT_PROFILE));
        driver.findElement(SAVE_EDIT_PROFILE).click();
    }

    @Step("Validate data on profile page")
    public void validateProfile(Profile profile) {
        log.info("Validating data on profile page");
        validateInput("Gender:", String.format("Gender: " + profile.getGender().substring(0, 1).toUpperCase() + profile.getGender().substring(1).toLowerCase()));
        validateInput("Birthday:", String.format("Birthday: " + profile.getBirthday()));
        validateInput("Weight:", String.format("Weight: %s %s", profile.getWeight(), profile.getWeightMeasure()));
        validateInput("Country:", String.format("Country: " + profile.getCountry()));
        validateInput("State:", String.format("State: " + profile.getRegion()));
        validateInput("City:", String.format("City: " + profile.getCity()));
        validateInput("Zip/Postal Code:", String.format("Zip/Postal Code: " + profile.getZip()));
    }


    @Step("Add photo to the user profile")
    public void uploadPhoto(String path) {
        driver.findElement(By.id("UserThumbnail")).click();
        WebElement uploader = driver.findElement(By.id("uploader"));
        driver.switchTo().frame(uploader);
        File file = new File(path);
        log.info("switched to frame");
        driver.findElement(By.cssSelector("[type = 'file']")).sendKeys(file.getAbsolutePath());
        driver.switchTo().defaultContent();
    }

    @Step("Save photo")
    public void savePhoto() throws InterruptedException {
        driver.findElement(By.id("NextStep")).click();
        Thread.sleep(7000);
        driver.findElement(By.id("NextStep")).click();
        Thread.sleep(7000);
    }

    @Step("Delete photo")
    public void deletePhoto() {
        driver.findElement(DELETE_PHOTO_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_DELETE_PHOTO));
        driver.findElement(SUBMIT_DELETE_PHOTO).click();
    }

    @Step("Clean profile fields")
    public void clean() {
        driver.findElement(By.id(bdayId)).clear();
        driver.findElement(By.id(weightId)).clear();
        driver.findElement(By.id(cityId)).clear();
        driver.findElement(By.id(zipId)).clear();
    }

}
