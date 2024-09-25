package tests;

import helper.HelpProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.MainPage;

public abstract class BaseTest {
    protected static MainPage page;
    protected static WebDriver driver;

    @BeforeClass
    public static void setup() {
        HelpProperties.createProperty();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        page = new MainPage();


    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
