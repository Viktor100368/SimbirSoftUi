package pages;

import helper.HelpCreateCustomer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCustomerPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder=\"First Name\"]")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[@placeholder=\"Last Name\"]")
    private WebElement lastNameField;
    @FindBy(xpath = "//input[@placeholder=\"Post Code\"]")
    private WebElement postCodeField;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement addNewCustomer;
    @FindBy(xpath = "//button[@ng-class=\"btnClass3\"]")
    private WebElement customerButton;
    @FindBy(xpath = "//input[@placeholder=\"Search Customer\"]")
    private WebElement searchCustomer;
    @FindBy(xpath = "//tr[@class=\"ng-scope\"]/td[1]")
    private static WebElement findingElement;
    @FindBy(xpath = "//button[@ng-class=\"btnClass1\"]")
    private static WebElement addCustomer;

    public CreateCustomerPage() { //Constructor
        PageFactory.initElements(driver, this);
    }

    public static WebElement getFindingElement() {
        return findingElement;
    }

    /**
     * метод заполняет поля формы и создает пользователя
     *
     * @return возвращает текущую страницу
     */
    public CreateCustomerPage creatingUser() {
        HelpCreateCustomer.generatePostCode();
        firstNameField.sendKeys(HelpCreateCustomer.generateFirstName(HelpCreateCustomer.getPostCode()));
        lastNameField.sendKeys(HelpCreateCustomer.generatedString());
        postCodeField.sendKeys(HelpCreateCustomer.getPostCode());
        addNewCustomer.submit();
        driver.switchTo().alert().accept();
        customerButton.click();
        searchCustomer.sendKeys(HelpCreateCustomer.getResult());
        return this;
    }
}
