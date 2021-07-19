package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ReportsPage extends BasePage {
    public static final By OPEN_REPORTS_BUTTON = By.xpath("//a[@class = 'ptip_s' and @href = 'WorkoutReport.cshtml']");
    public static final By BREADCRUMB = By.xpath("//a[contains( text(),'Athlete Workout Report')]");
    public static final By START_DATE = By.id("WorkoutDate");
    public static final By END_DATE = By.id("WorkoutDateEnd");
    public static final By VIEW_REPORTS_BUTTON = By.id("saveButton");
    String reportWorkoutName = "[data-title = '%s']";


    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check that Reports page is opened")
    public boolean isPageOpened() {
        log.info("Check that Reports page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }

    @Step("Open reports page")
    public void openReportsPage() {
        try {
            driver.findElement(OPEN_REPORTS_BUTTON).click();
        } catch (ElementClickInterceptedException e) {
            log.warn(e.getLocalizedMessage());
            WebElement element = driver.findElement(OPEN_REPORTS_BUTTON);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    @Step("Check that workout was added to the report")
    public boolean checkThatWorkoutIsAddedToReports(String date, String workoutName) {
        driver.findElement(START_DATE).clear();
        driver.findElement(START_DATE).sendKeys(date);
        driver.findElement(END_DATE).clear();
        driver.findElement(END_DATE).sendKeys(date);
        driver.findElement(VIEW_REPORTS_BUTTON).click();
        log.info("Element with " + workoutName + " is " + isExist(By.cssSelector(String.format(reportWorkoutName, workoutName))));
        return isExist(By.cssSelector(String.format(reportWorkoutName, workoutName)));
    }
}
