package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage{
    public static final By USERNAME_INPUT = By.id("login_name");
    public static final By PASSWORD_INPUT = By.id("login_password");
    public static final By LOGIN_BUTTON = By.cssSelector("[type = 'submit']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Login page")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Log in with a user")
    public HomePage login(String user, String password){
        log.info("Log in with user and password");
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new HomePage(driver);

    }

    @Step("Check that page was opened")
    public boolean isPageOpened() {
        log.info("Validating that login button %s exists",LOGIN_BUTTON);
        return isExist(LOGIN_BUTTON);
    }
}
