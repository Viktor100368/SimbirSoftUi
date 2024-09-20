package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class MainPage extends BasePage {
    Properties properties = new Properties();
    @FindBy(xpath = "//button[@ng-class=\"btnClass1\"]")
    private WebElement addCustomer;
    @FindBy(xpath = "//button[@ng-class=\"btnClass3\"]")
    private WebElement customersButton;


    public MainPage() {

        driver.get(getProperty(properties, "url"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }


    public MainPage startCreatingUsers() {
        clickAddCustomerButton();
        return this;
    }

    public MainPage startSorting() {
        addCustomer.click();
        clickCustomerButton();
        return this;
    }

    public MainPage deleteCustomer() {
        clickForDeleting();
        return this;
    }


    //создание нового Customer
    public CreateCustomerPage clickAddCustomerButton() {
        addCustomer.click();
        return new CreateCustomerPage();
    }

    //сортировка customers
    public SortingCustomersPage clickCustomerButton() {
        customersButton.click();
        return new SortingCustomersPage();
    }

    public DeleteCustomerPage clickForDeleting() {
        customersButton.click();
        return new DeleteCustomerPage();
    }

    public String getProperty(Properties properties, String key) {
        String url;

        try (FileInputStream fis = new FileInputStream("./src/test/resources/local.properties")) {
            properties.load(fis);
            url = properties.getProperty(key);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

}
