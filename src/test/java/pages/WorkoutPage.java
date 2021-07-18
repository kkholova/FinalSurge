package pages;

import elements.InputHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.WorkoutQuickAdd;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Log4j2
public class WorkoutPage extends BasePage {
    public static final By QUICK_ADD_BUTTON = By.id("QuickAddToggle");
    public static final By QUICK_ADD_FORM = By.id("QuickAdd");
    public static final By SAVE_QUICK_ADD_WORKOUT = By.id("saveButton");
    public static final By POPUP_SAVE_BUTTON = By.xpath("//div[@class = 'modal-footer']//a[contains(text(), 'OK')]");
    public static final By WORKOUT_DATE = By.id("WorkoutDate");
    public static final By ALERT = By.cssSelector(".alert");
    String quickDelete = "//div[contains(text(),'%s')]/ancestor::div[contains(@class , 'dropdown')]/ul//li/a[contains(@class, 'quick-delete')]";
    String calendarTitleLocator = "//div[contains(text(),'%s')]";

    public WorkoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        log.info("Check that workout quick add form page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(QUICK_ADD_FORM));
        return isExist(QUICK_ADD_FORM);
    }

    @Step("Open workout quick add page")
    public void openWorkoutQuickAdd() {
        driver.findElement(QUICK_ADD_BUTTON).click();
    }


    @Step("Add workout through the quick add")
    public void workOutQuickAdd(WorkoutQuickAdd workout) {
        log.info("Adding new workout");
        log.info("Add workout date");
        driver.findElement(WORKOUT_DATE).clear();
        new InputHelper(driver, "WorkoutDate").writeText(workout.getDate());
        log.info("Add time of workout " + workout.getTime());
        new InputHelper(driver, "WorkoutTime").writeText(workout.getTime());
        log.info("Add activity type " + workout.getActivityType());
        new InputHelper(driver, "ActivityType").selectFromDropdown(workout.getActivityType());
        log.info("Add workout name " + workout.getWorkoutName());
        new InputHelper(driver, "Name").writeText(workout.getWorkoutName());
        log.info("Add workout description " + workout.getDescription());
        new InputHelper(driver, "Desc").writeText(workout.getDescription());
        log.info("Choose planned workout " + workout.isPlanned());
        new InputHelper(driver, "PlannedWorkout").tickCheckbox(workout.isPlanned());
        if (workout.isPlanned()) {
            log.info("Add planned distance " + workout.getPlannedDistance());
            new InputHelper(driver, "PDistance").writeText(workout.getPlannedDistance());
            log.info("Add planned duration " + workout.getPlannedDuration());
            new InputHelper(driver, "PDuration").writeText(workout.getPlannedDuration());
        }
        log.info("Add distance " + workout.getDistance());
        new InputHelper(driver, "Distance").writeText(workout.getDistance());
        log.info("Add duration " + workout.getDuration());
        new InputHelper(driver, "Duration").writeText(workout.getDuration());
        log.info("Choose how you feel " + workout.getHowIFelt());
        new InputHelper(driver, "HowFeel").selectFromDropdown(workout.getHowIFelt());
        log.info("Choose effort " + workout.getPerceivedEffort());
        new InputHelper(driver, "PerEffort").selectFromDropdown(workout.getPerceivedEffort());
        log.info("Choose save to Library " + workout.isSavedToLibrary());
        new InputHelper(driver, "SaveLibrary").tickCheckbox(workout.isSavedToLibrary());
    }

    @Step("Save workout quick add form")
    public void saveQuickAddForm() {
        log.info("Click on save button" + SAVE_QUICK_ADD_WORKOUT);
        driver.findElement(SAVE_QUICK_ADD_WORKOUT).click();
    }

    @Step("Take alert message after workout is added")
    public String getAlert() {
        return driver.findElement(ALERT).getText();
    }

    @Step("Check that workout with {workoutTitle} was added to the calendar")
    public boolean checkWorkoutWasAddedToCalendar(String workoutTitle) {
        log.info("Check workout with name " + workoutTitle + " was added");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(calendarTitleLocator, workoutTitle))));
        return driver.findElement(By.xpath(String.format(calendarTitleLocator, workoutTitle))).isDisplayed();
    }

    @Step("Delete workout with {workoutTitle}")
    public void deleteWorkout(String workoutTitle) {
        driver.findElement(By.xpath(String.format(calendarTitleLocator, workoutTitle))).click();
        log.info("Delete workout " + workoutTitle);
        driver.findElement(By.xpath(String.format(quickDelete, workoutTitle))).click();
        try {
            driver.findElement(POPUP_SAVE_BUTTON).click();
        } catch (ElementClickInterceptedException e) {
            log.warn(e.getLocalizedMessage());
            WebElement element = driver.findElement(POPUP_SAVE_BUTTON);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    @Step("Check that workout with {workoutTitle} was deleted from the calendar")
    public boolean checkWorkoutWasDeletedFromCalendar(String workoutTitle) {
        log.info("Check workout with name " + workoutTitle + " was deleted");
        driver.findElement(By.xpath(String.format(calendarTitleLocator, workoutTitle))).isDisplayed();
        return false;
    }

}
