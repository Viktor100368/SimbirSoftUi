package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SortingCustomersPage extends BasePage {
    private static List<String> listName = new ArrayList<>();

    public static List<String> getListName() {
        return listName;
    }

    @FindBy(xpath = "//a[@href=\"#\"]")
    private WebElement sortingCustomers;
    @FindBy(xpath = "//tr[@class=\"ng-scope\"]/td[1]")
    private List<WebElement> nameCustomers;

    public SortingCustomersPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * метод сортирует клиентов по именам и складывает их в лист.
     * При первом нажатии на FirstName, сортиравка по уибыванию,
     * при повторнрм по возрастанию имен, чисто для удоства проверки правильности сортировки
     */
    public List<String> sortingCustomers() {
        sortingCustomers.click();
        sortingCustomers.click();
        for (WebElement el : nameCustomers) {
            listName.add(el.getText());
        }
        return listName;
    }
}
