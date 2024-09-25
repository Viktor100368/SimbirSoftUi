package pages;

import helper.HelpProperties;
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


    public MainPage() {//constructor
        driver.get(HelpProperties.getProperty(properties, "url"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    /**
     * инициализирует создание Customer
     *
     * @return текущую страницу, для chainOfInvokes
     */
    public MainPage startCreatingUsers() {
        clickAddCustomerButton();
        return this;
    }

    /**
     * инициализирует сортировку
     *
     * @return текущую страницу, для chainOfInvokes
     */
    public MainPage startSorting() {
        addCustomer.click();
        clickCustomerButton();
        return this;
    }

    /**
     * инициолизирует удаление Customers
     *
     * @return текущую страницу, для chainOfInvokes
     */
    public MainPage deleteCustomer() {
        clickForDeleting();
        return this;
    }


    /**
     * создает Customer
     *
     * @return страницу создания Customer, chainOfInvokes
     */
    public CreateCustomerPage clickAddCustomerButton() {
        addCustomer.click();
        return new CreateCustomerPage();
    }

    /**
     * сортирует Customers
     *
     * @return страницу сортировки, chainOfInvokes
     */
    public SortingCustomersPage clickCustomerButton() {
        customersButton.click();
        return new SortingCustomersPage();
    }

    /**
     * удаление target Customers
     *
     * @return страинцу удаления, chainOfInvokes
     */
    public DeleteCustomerPage clickForDeleting() {
        customersButton.click();
        return new DeleteCustomerPage();
    }


}
