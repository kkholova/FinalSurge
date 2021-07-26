package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class WorkoutDetailsPage extends BasePage {
    public static final By BREADCRUMB = By.xpath("//ul[@id=  'breadcrumbs']//a[contains(text(),'Workout Details')]");

    public WorkoutDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        log.info("Check that Add workout page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }
}
