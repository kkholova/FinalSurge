package tests;

import models.WorkoutAddFactory;
import models.WorkoutQuickAdd;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkoutTest extends BaseTest {

    @Test(description = "Open workout quick add form")
    public void workoutQuickAddFormShouldBeOpened() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        workoutPage.openWorkoutQuickAdd();
        Assert.assertTrue(workoutPage.isPageOpened(), "Profile page wasn't opened");
    }

    @Test(description = "Add workout with quick add")
    public void workoutShouldBeAddedWithQuickAdd() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = WorkoutAddFactory.get();
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        Assert.assertTrue(workoutPage.checkWorkoutWasAddedToCalendar(
                workout.getWorkoutName()), "Workout was not added to the calendar");
    }

    @Test(description = "Add workout with quick add and check that it was saved to the library")
    public void workoutShouldBeAddedToTheLibrary() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd fakeWorkout = WorkoutAddFactory.get();
        WorkoutQuickAdd workout = new WorkoutQuickAdd(fakeWorkout.getDate(), fakeWorkout.getTime(), fakeWorkout.getActivityType(), fakeWorkout.getWorkoutName(),
                fakeWorkout.getDescription(), true, fakeWorkout.getPlannedDistance(), fakeWorkout.getPlannedDuration(), fakeWorkout.getDistance(),
                fakeWorkout.getDuration(), fakeWorkout.getHowIFelt(), fakeWorkout.getPerceivedEffort(), true);
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        Assert.assertEquals(workoutPage.getAlert(), "×\n" +
                "*The workout was successfully saved to your Workout Library.", "Workout wasn't added to the library");
        Assert.assertTrue(workoutPage.checkWorkoutWasAddedToCalendar(
                workout.getWorkoutName()), "Workout was not added to the calendar");
    }

    @Test(description = "Check that workout was deleted after adding")
    public void workoutShouldBeDeleted() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout = WorkoutAddFactory.get();
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        Assert.assertTrue(workoutPage.checkWorkoutWasAddedToCalendar(
                workout.getWorkoutName()), "Workout was not added to the calendar");
        workoutPage.deleteWorkout(workout.getWorkoutName());
        Assert.assertFalse(workoutPage.checkWorkoutWasDeletedFromCalendar(
                workout.getWorkoutName()), "Workout was not deleted");
    }

    @Test(description = "Check that workout can not be added without Activity type")
    public void workoutWithoutActivityTypeShouldNotBeAdded() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        WorkoutQuickAdd workout1 = WorkoutAddFactory.get();
        WorkoutQuickAdd workout = new WorkoutQuickAdd(workout1.getDate(), workout1.getTime(), "Select...", workout1.getWorkoutName(),
                "fjfjf", true, "10", "00:22:00", "5", "00:20:00", "Good",
                "2 (Light)", true);
        workoutPage.openWorkoutQuickAdd();
        workoutPage.workOutQuickAdd(workout);
        workoutPage.saveQuickAddForm();
        Assert.assertEquals(workoutPage.getAlert(), "×\n" +
                "Please fix the following errors:\n" +
                "*Please select a valid Activity Type.", "Workout was added without Activity type");
    }

}
