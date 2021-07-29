package tests;

import models.AddNewType;
import models.AddNewTypeFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewTypeTest extends BaseTest {

    @Test(description = "Check that activities page was opened")
    public void activitiesPageShouldBeOpened() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        addNewTypePage
                .openFullAddPage()
                .clickOnAddNewType();
        Assert.assertTrue(addNewTypePage.isPageOpened(), "Activities Types page was not opened");
    }

    @Test(description = "Check that new workout type was added")
    public void newActivityTypeShouldBeAdded() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        AddNewType newActivity = AddNewTypeFactory.get();
        addNewTypePage
                .openFullAddPage()
                .clickOnAddNewType()
                .createNewWorkoutType(newActivity)
                .saveNewTypeButton();
        Assert.assertTrue(addNewTypePage.isNewWorkoutTypeAdded(
                newActivity.getNewTypeName()), "New Activity type was not added");
        addNewTypePage.deleteNewActivityType(newActivity.getNewTypeName());
    }

    @Test(description = "Check that new workout type was deleted")
    public void newActivityTypeShouldBeDeleted() {
        loginPage
                .open(baseUrl)
                .login(email, password);
        calendarPage.openCalendarPage();
        AddNewType newActivity = AddNewTypeFactory.get();
        addNewTypePage
                .openFullAddPage()
                .clickOnAddNewType()
                .createNewWorkoutType(newActivity)
                .saveNewTypeButton()
                .deleteNewActivityType(newActivity.getNewTypeName());
        Assert.assertFalse(addNewTypePage.isNewWorkoutTypeDeleted(
                newActivity.getNewTypeName()), "New Activity type was not deleted");

    }
}
