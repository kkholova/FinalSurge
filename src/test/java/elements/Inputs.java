package elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

@Log4j2
public class Inputs {
    WebDriver driver;
    WebDriverWait wait;
    String id;
    String path;
    String locator = "%s";

    public Inputs(WebDriver driver, String id) {
        this.driver = driver;
        this.id = id;
        wait = new WebDriverWait(driver, 20);
    }

    @Step("Fill Input fields with data: {text}")
    public void writeText(String text) {
        log.info("Fill in a field");
        driver.findElement(By.id(String.format(locator, id))).sendKeys(text);

    }

    @Step("Fill Input fields with data: {text}")
    public void tickCheckbox(String option) {
        log.info("Fill in a field , with text, by locator ");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(String.format(locator, id))));
            driver.findElement(By.id(String.format(locator, id))).click();
        } catch (ElementClickInterceptedException e) {
            log.warn(e.getLocalizedMessage());
            WebElement element = driver.findElement(By.id(String.format(locator, id)));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    @Step("Fill Input fields with data: {text}")
    public void selectFromDropdown(String option) {
        log.info("Fill in a field , with text , by locator ");
        WebElement dropdown = driver.findElement(By.id(String.format(locator, id)));
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

//    @Step("Fill Input fields with data: {text}")
//    public void uploadPhoto(String path) {
//        log.info("Fill in a field");
//        File file = new File(path);
//        driver.findElement(By.id("UserThumbnail")).click();
////        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("profilepic")));
//        driver.findElement(By.name("profilepic")).sendKeys(file.getAbsolutePath());
//        driver.findElement(By.id("NextStep")).click();
//    }
}
