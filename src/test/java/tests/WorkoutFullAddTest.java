package tests;

import models.WorkoutAddFactory;
import models.WorkoutFullAdd;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorkoutFullAddTest extends BaseTest {

    @DataProvider(name = "Activity type data")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"run"},
                {"bike"},
                {"rest"},
        };
    }

    @Test(description = "Check that workout full add page was opened")
    public void activitiesPageShouldBeOpened() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        workoutFullAddPage.openWorkoutFullAdd();
        Assert.assertTrue(workoutFullAddPage.isPageOpened(), "Workout Full Add page was not opened");
    }

    @Test(description = "Add workout with quick add", dataProvider = "Activity type data")
    public void workoutShouldBeAddedWithFullAdd(String activityType) {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutFullAdd workout = WorkoutAddFactory.getFullAdd();
        workoutFullAddPage
                .openWorkoutFullAdd()
                .fillInWorkoutWithFullAdd(workout, activityType)
                .saveFullAddForm();
        Assert.assertTrue(workoutDetailsPage.isPageOpened(), "Workout Full Add page was not opened");
    }
}
