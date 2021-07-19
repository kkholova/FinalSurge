package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;

@Log4j2
public class CalendarPage extends BasePage {
    public static final By OPEN_CALENDAR_BUTTON = By.xpath("//a[@class = 'ptip_s' and @href = 'Calendar.cshtml']");
    public static final By BREADCRUMB = By.xpath("//a[contains( text(),'Training Calendar')]");
    String elementToDrug = "//div[contains(text(),'%s')]/ancestor::div[contains(@class , 'dropdown')]/ancestor::td";
    String elementToDropIn = "//div[contains(@class , 'dropdown')]/ancestor::td[@data-day = '%s' and @data-month = '%s' and @data-year = '%s']";

    @Step("Open Calendar page")
    public void openCalendarPage() {
        driver.findElement(OPEN_CALENDAR_BUTTON).click();
    }

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check that Calendar page was opened")
    public boolean isPageOpened() {
        log.info("Check that Calendar page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BREADCRUMB));
        return isExist(BREADCRUMB);
    }


    @Step("Drag and drop a workout in the calendar")
    public void dragAndDrop(String workoutName) {
        int currentDay = LocalDate.now().getDayOfMonth();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        String day = String.format("%s", currentDay + 1);
        log.info("Find element");
        WebElement element = driver.findElement(By.xpath(String.format(elementToDrug, workoutName)));
        log.info("find target");
        WebElement target = driver.findElement(By.xpath(String.format(elementToDropIn, day, currentMonth, currentYear)));
        (new Actions(driver)).dragAndDrop(element, target).perform();
    }
}
