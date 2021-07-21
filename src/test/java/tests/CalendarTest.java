package tests;

import models.QuickAddFactory;
import models.WorkoutQuickAdd;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {

    @Test(description = "Open edit profile page")
    public void calendarPageShouldBeOpened() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page wasn't opened");
    }

    @Test(description = "Test that workout could be moved to the other date with drag-and-drop")
    public void workoutShouldBeDragAndDropped() {
        loginPage
                .open()
                .login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = QuickAddFactory.get();
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        calendarPage.dragAndDrop(workout.getWorkoutName());
        Assert.assertTrue(workoutPage.checkWorkoutWasAddedToCalendar(
                workout.getWorkoutName()), "Workout was not added to the calendar");
    }
}
