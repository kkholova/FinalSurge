package tests;

import models.WorkoutAddFactory;
import models.WorkoutQuickAdd;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {

    @Test(description = "Open edit profile page")
    public void calendarPageShouldBeOpened() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page wasn't opened");
    }

    @Test(description = "Test that workout could be moved to the other date with drag-and-drop")
    public void workoutShouldBeDragAndDropped() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = WorkoutAddFactory.get();
        workoutPage
                .openWorkoutQuickAdd()
                .workOutQuickAdd(workout)
                .saveQuickAddForm();
        calendarPage.dragAndDropWorkoutToNewDate(workout.getWorkoutName());
        Assert.assertTrue(workoutPage.checkWorkoutWasAddedToCalendar(
                workout.getWorkoutName()), "Workout was not added to the calendar");
    }
}
