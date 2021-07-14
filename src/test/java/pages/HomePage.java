package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public static final By PROFILE_ICON = By.id("LayoutProfilePic");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return isExist(PROFILE_ICON);
    }
}
