package pages;

import elements.InputHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.AddNewType;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Log4j2
public class AddNewTypePage extends BasePage {
    public static final By FULL_ADD_BUTTON = By.id("FullAddBtn");
    public static final By BREADCRUMB = By.xpath("//ul[@id=  'breadcrumbs']//a[@href = 'Activities.cshtml']");
    public static final By SAVE_NEW_TYPE_BUTTON = By.id("saveButton");
    public static final By DELETE_ACTIVITY_TYPE_BUTTON = By.id("del-acttype");
    public static final By ADD_NEW_TYPE_BUTTON = By.xpath("//a[@class = 'accordion-toggle' and @href = 'Activities.cshtml']");
    public static final By SUBMIT_DELETE_ACTIVITY_TYPE = By.xpath("//div[@class = 'modal-footer']//a[contains(text(),'OK')]");
    String newTypeNameLocator = "//div[@class = 'w-box-header']/*[contains(text(),'%s')]";
    String editActivityTypeLocator = "//div[@class = 'w-box-header']/*[contains(text(),'%s')]//following::div//*[contains(@title, 'Edit')]";

    public AddNewTypePage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check that Activities types page was opened")
    public boolean isPageOpened() {
        log.info("Check that Activities types page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }

    @Step("Click on workout FUll Add button")
    public AddNewTypePage openFullAddPage() {
        driver.findElement(FULL_ADD_BUTTON).click();
        return new AddNewTypePage(driver);
    }

    @Step("Click on Add New type button")
    public AddNewTypePage clickOnAddNewType() {
        driver.findElement(ADD_NEW_TYPE_BUTTON).click();
        return new AddNewTypePage(driver);
    }

    @Step("Choose text color {color}")
    public String chooseTextColor(String color) {
        switch (color) {
            case "white":
                color = "TC1";
                break;
            case "black":
                color = "TC2";
                break;
        }
        return color;
    }

    @Step("Edit User profile")
    public AddNewTypePage createNewWorkoutType(AddNewType newType) {
        String color = newType.getTextColor();
        log.info("Creating new workout type");
        log.info("Set new workout type Name");
        new InputHelper(driver, "ATypeName").writeText(newType.getNewTypeName());
        log.info("Choose text color " + color);
        new InputHelper(driver, chooseTextColor(color)).tickRadioButton(chooseTextColor(color));
        return new AddNewTypePage(driver);
    }

    @Step("Save New Activity type button")
    public AddNewTypePage saveNewTypeButton() {
        driver.findElement(SAVE_NEW_TYPE_BUTTON).click();
        return new AddNewTypePage(driver);
    }

    @Step("Check that new workout type was added")
    public boolean isNewWorkoutTypeAdded(String newTypeName) {
        log.info("Check workout with name " + newTypeName + " was added");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(newTypeNameLocator, newTypeName))));
        return driver.findElement(By.xpath(String.format(newTypeNameLocator, newTypeName))).isDisplayed();
    }

    @Step("Edit Activity type")
    public void openEditActivityType(String newTypeName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(editActivityTypeLocator, newTypeName))));
            driver.findElement(By.xpath(String.format(editActivityTypeLocator, newTypeName))).click();
        } catch (ElementNotInteractableException e) {
            log.warn(e.getLocalizedMessage());
            WebElement element = driver.findElement(By.xpath(String.format(editActivityTypeLocator, newTypeName)));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    @Step("Delete new activity type")
    public AddNewTypePage deleteNewActivityType(String newTypeName) {
        log.info("Workout with name " + newTypeName + " should be deleted");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(newTypeNameLocator, newTypeName))));
        driver.findElement(By.xpath(String.format(newTypeNameLocator, newTypeName))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(editActivityTypeLocator, newTypeName))));
        log.info("Open Edit activity type");
        openEditActivityType(newTypeName);
        log.info("Click on delete button");
        driver.findElement(DELETE_ACTIVITY_TYPE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_DELETE_ACTIVITY_TYPE));
        driver.findElement(SUBMIT_DELETE_ACTIVITY_TYPE).click();
        return new AddNewTypePage(driver);
    }

    @Step("Check that new workout type was deleted")
    public boolean isNewWorkoutTypeDeleted(String newTypeName) {
        log.info("Check workout with name " + newTypeName + " was deleted");
        try {
            return driver.findElement(By.xpath(String.format(newTypeNameLocator, newTypeName))).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
