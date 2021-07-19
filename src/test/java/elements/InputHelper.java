package elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

@Log4j2
public class InputHelper {
    WebDriver driver;
    WebDriverWait wait;
    String id;

    public InputHelper(WebDriver driver, String id) {
        this.driver = driver;
        this.id = id;
        wait = new WebDriverWait(driver, 20);
    }

    @Step("Fill Input field with data {text}")
    public void writeText(String text) {
        log.info("Fill in a field with text: " + text);
        driver.findElement(By.id(id)).sendKeys(text);

    }
    
    public boolean isRadioButtonAlreadyChosen() {
        return driver.findElement(By.id(id)).isSelected();
    }

    @Step("Click radiobutton with option")
    public void tickRadioButton(String option) {
        log.info("Choose radiobutton with id " + id);
        boolean alreadySelected = isRadioButtonAlreadyChosen();
        if (!alreadySelected) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
                driver.findElement(By.id(id)).click();
            } catch (ElementClickInterceptedException e) {
                log.warn(e.getLocalizedMessage());
                WebElement element = driver.findElement(By.id(id));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
            }
        }
    }

    @Step("Tick checkbox as {isTrue}")
    public void tickCheckbox(boolean isTrue) {
        log.info("Tick checkbox " + isTrue);
        if(isTrue) {
            driver.findElement(By.id(id)).click();
        }
    }

    @Step("Select option {option} from dropdown")
    public void selectFromDropdown(String option) {
        log.info("Select option " + option + " from dropdown with id: " + id);
        WebElement dropdown = driver.findElement(By.id(id));
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }
}
