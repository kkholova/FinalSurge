package tests;

import models.QuickAddFactory;
import models.WorkoutQuickAdd;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportsTest extends BaseTest {

    @Test(description = "Open reports page")
    public void reportsPageShouldBeOpened() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        reportsPage.openReportsPage();
        Assert.assertTrue(reportsPage.isPageOpened(), "Reports page wasn't opened");
    }

    @Test(description = "Test that workout was added to the reports")
    public void workoutShouldBeAddedToReports() {
        loginPage
                .open()
                .login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = QuickAddFactory.get();
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        reportsPage.openReportsPage();
        Assert.assertTrue(reportsPage.checkThatWorkoutIsAddedToReports(workout.getDate(), workout.getWorkoutName()),
                "Workout was not added to the reports");
    }
}
