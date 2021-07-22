package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import tests.base.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    ProfilePage profilePage;
    CalendarPage calendarPage;
    WorkoutPage workoutPage;
    ReportsPage reportsPage;
    AddNewTypePage addNewTypePage;
    protected String baseUrl = utils.PropertyReader.getProperty("FINALSURGE_URL", "finalsurge.baseUrl");
    protected String email = utils.PropertyReader.getProperty("FINALSURGE_EMAIL", "finalsurge.email");
    protected String password = utils.PropertyReader.getProperty("FINALSURGE_PASSWORD", "finalsurge.password");
//    public static String USER = "katekholova@gmail.com";
//    public static String PASSWORD = "Kk!7571255";

    @Parameters({"browser"})
    @BeforeMethod(description = "Open browser")
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) {
        if (browser.equals(("chrome"))) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver();
        }
        testContext.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//неявные ожидания
        wait = new WebDriverWait(driver, 25);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profilePage = new ProfilePage(driver);
        calendarPage = new CalendarPage(driver);
        workoutPage = new WorkoutPage(driver);
        reportsPage = new ReportsPage(driver);
        addNewTypePage = new AddNewTypePage(driver);
    }


    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        driver.quit();
    }

}
