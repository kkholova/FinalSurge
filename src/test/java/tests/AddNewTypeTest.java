package tests;

import models.AddNewType;
import models.AddNewTypeFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewTypeTest extends BaseTest {

    @Test(description = "Check that activities page was opened")
    public void activitiesPageShouldBeOpened() {
        loginPage
                .open()
                .login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        addNewTypePage.openFullAddPage();
        addNewTypePage.clickOnAddNewType();
        Assert.assertTrue(addNewTypePage.isPageOpened(), "Activities Types page was not opened");
    }

    @Test(description = "Check that new workout type was added")
    public void newActivityTypeShouldBeAdded() {
        loginPage
                .open()
                .login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        addNewTypePage.openFullAddPage();
        addNewTypePage.clickOnAddNewType();
        AddNewType newActivity = AddNewTypeFactory.get();
        addNewTypePage.createNewWorkoutType(newActivity);
        addNewTypePage.saveNewTypeButton();
        Assert.assertTrue(addNewTypePage.isNewWorkoutTypeAdded(
                newActivity.getNewTypeName()), "New Activity type was not added");

    }

    @Test(description = "Check that new workout type was deleted")
    public void newActivityTypeShouldBeDeleted() {
        loginPage
                .open()
                .login(USER, PASSWORD);
        calendarPage.openCalendarPage();
        addNewTypePage.openFullAddPage();
        addNewTypePage.clickOnAddNewType();
        AddNewType newActivity = AddNewTypeFactory.get();
        addNewTypePage.createNewWorkoutType(newActivity);
        addNewTypePage.saveNewTypeButton();
        addNewTypePage.deleteNewActivityType(newActivity.getNewTypeName());
        Assert.assertFalse(addNewTypePage.isNewWorkoutTypeDeleted(
                newActivity.getNewTypeName()), "New Activity type was not deleted");

    }
}
