package tests;

import models.WorkoutAddFactory;
import models.WorkoutQuickAdd;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportsTest extends BaseTest {

    @Test(description = "Open reports page")
    public void reportsPageShouldBeOpened() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        reportsPage.openReportsPage();
        Assert.assertTrue(reportsPage.isPageOpened(), "Reports page wasn't opened");
    }

    @Test(description = "Check that workout was added to the reports")
    public void workoutShouldBeAddedToReports() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = WorkoutAddFactory.get();
        workoutPage
                .openWorkoutQuickAdd()
                .workOutQuickAdd(workout)
                .saveQuickAddForm();
        reportsPage.openReportsPage();
        Assert.assertTrue(reportsPage.checkThatWorkoutIsAddedToReports(workout.getDate(), workout.getWorkoutName()),
                "Workout was not added to the reports");
    }
}
