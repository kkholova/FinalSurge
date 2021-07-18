package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CalendarPage extends BasePage {
    public static final By OPEN_CALENDAR_BUTTON = By.xpath("//a[@class = 'ptip_s' and @href = 'Calendar.cshtml']");
    public static final By BREADCRUMB = By.xpath("//a[contains( text(),'Training Calendar')]");

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

//TODO  finish test

//    @Step("Drag and drop a workout in the calendar")
//    public void dragAndDrop(String workoutName){
//        //div[contains(text(),'Tabata')]/ancestor::div[contains(@class , 'dropdown')]
//
//        WebElement element = driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]/ancestor::div[contains(@class , 'dropdown')]/ancestor::td",workoutName)));
//       String fromDate = driver.findElement(By.xpath("//div[contains(text(),'%s')]/ancestor::div[contains(@class , 'dropdown')]/ancestor::td")).getAttribute("data-day");
//        System.out.println(fromDate);
////        String toDate =//div[contains(text(),'Tabata')]/ancestor::div[contains(@class , 'dropdown')]/ancestor::td/following-sibling::td
//        WebElement target = driver.findElement(By.xpath(String.format("div[contains(text(),'%s')]/ancestor::div[contains(@class , 'dropdown')]/ancestor::td/following-sibling::td",workoutName)));
//
//        (new Actions(driver)).dragAndDrop(element, target).perform();
//
//    }
}
