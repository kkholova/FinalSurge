package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public abstract class BasePage {

    public static final String BASE_URL ="https://log.finalsurge.com/";
    WebDriver driver;
    WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,25);

    }
    public abstract boolean isPageOpened();

    @Step ("Check that element exists")
    public boolean isExist(By locator){
        try{
            log.info("Check that element %s is on the page",locator);
            driver.findElement(locator);
            return true;
        }catch(NoSuchElementException exception){
            log.warn("Element is not exist on page");
            log.warn(exception.getLocalizedMessage());
            return false;
        }

    }

}
