package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {
    public static final By USERNAME_INPUT = By.id("login_name");
    public static final By PASSWORD_INPUT = By.id("login_password");
    public static final By LOGIN_BUTTON = By.cssSelector("[type = 'submit']");
    public static final By ERROR_MESSAGE = By.cssSelector("label[class='error']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Login page")
    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        log.info("Click on Login button");
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Log in with a user")
    public HomePage login(String email, String password) {
        log.info("Log in with user " + email + " and password " + password);
        driver.findElement(USERNAME_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        clickOnLoginButton();
        return new HomePage(driver);
    }

    public String takeErrorMessage() {
        log.info("Take error message");
        String errorMessage = driver.findElement(ERROR_MESSAGE).getText();
        return errorMessage;
    }

    @Step("Check that page was opened")
    public boolean isPageOpened() {
        log.info("Validating that login button %s exists", LOGIN_BUTTON);
        return isExist(LOGIN_BUTTON);
    }
}
