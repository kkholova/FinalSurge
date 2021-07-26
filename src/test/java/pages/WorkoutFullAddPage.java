package pages;

import elements.InputHelper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.WorkoutFullAdd;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class WorkoutFullAddPage extends BasePage {
    public static final By FULL_ADD_BUTTON = By.id("FullAddBtn");
    public static final By BREADCRUMB = By.xpath("//ul[@id=  'breadcrumbs']//a[contains(text(),'Add Workout')]");
    public static final By WORKOUT_DATE = By.id("WorkoutDate");
    public static final By SAVE_FULL_ADD_WORKOUT = By.id("saveButton");

    public WorkoutFullAddPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check that workout full add page is opened")
    public boolean isPageOpened() {
        log.info("Check that Add workout page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }

    @Step("Open workout full add page")
    public WorkoutFullAddPage openWorkoutFullAdd() {
        driver.findElement(FULL_ADD_BUTTON).click();
        return new WorkoutFullAddPage(driver);
    }

    @Step("Fill in base fields for any Activity type")
    public void baseFieldsFillIn(WorkoutFullAdd workout) {
        driver.findElement(WORKOUT_DATE).clear();
        new InputHelper(driver, "WorkoutDate").writeText(workout.getDate());
        new InputHelper(driver, "Name").writeText(workout.getWorkoutName());
        new InputHelper(driver, "Desc").writeText(workout.getDescription());
    }

    @Step("Fill in common fields Activity types as bike,run,swim")
    public void commonFieldsFillIn(WorkoutFullAdd workout) {
        String feeling = workout.getHowIFelt();
        new InputHelper(driver, "WorkoutTime").writeText(workout.getTime());
        new InputHelper(driver, "PlannedWorkout").tickCheckbox(workout.isPlanned());
        if (workout.isPlanned()) {
            new InputHelper(driver, "PDistance").writeText(workout.getPlannedDistance());
            new InputHelper(driver, "PDuration").writeText(workout.getPlannedDuration());
        }
        new InputHelper(driver, "Distance").writeText(workout.getDistance());
        new InputHelper(driver, "Duration").writeText(workout.getDuration());
        new InputHelper(driver, selectHowIFelt(feeling)).tickRadioButton(selectHowIFelt(feeling));
        new InputHelper(driver, "PerEffort").selectFromDropdown(workout.getPerceivedEffort());
    }

    @Step("Fill in  fields Activity types as bike,swim")
    public void additionalFieldsFillIn(WorkoutFullAdd workout) {
        new InputHelper(driver, "EGain").writeText(workout.getElevationGain());
        new InputHelper(driver, "ELoss").writeText(workout.getElevationLoss());
        new InputHelper(driver, "kCal").writeText(workout.getCaloriesBurned());
    }

    @Step("Fill in full add form for {activityType} Activity type ")
    public WorkoutFullAddPage fillInWorkoutWithFullAdd(WorkoutFullAdd workout, String activityType) {
        switch (activityType) {
            case "run":
                driver.findElement(By.cssSelector("[data-code = 'run']")).click();
                baseFieldsFillIn(workout);
                commonFieldsFillIn(workout);
                break;
            case "rest":
                driver.findElement(By.cssSelector("[data-code = 'rest']")).click();
                baseFieldsFillIn(workout);
                break;
            case "bike":
                driver.findElement(By.cssSelector("[data-code = 'bike']")).click();
                baseFieldsFillIn(workout);
                commonFieldsFillIn(workout);
                additionalFieldsFillIn(workout);
                break;
        }
        return new WorkoutFullAddPage(driver);
    }


    public String selectHowIFelt(String howFeel) {
        switch (howFeel) {
            case "Great":
                howFeel = "hf_great";
                break;
            case "Good":
                howFeel = "hf_good";
                break;
            case "Normal":
                howFeel = "hf_normal";
                break;
            case "Poor":
                howFeel = "hf_poor";
                break;
            case "Terrible":
                howFeel = "hf_terrible";
                break;
        }
        return howFeel;
    }

    @Step("Save workout full add form")
    public void saveFullAddForm() {
        log.info("Click on save button" + SAVE_FULL_ADD_WORKOUT);
        driver.findElement(SAVE_FULL_ADD_WORKOUT).click();
    }

}
