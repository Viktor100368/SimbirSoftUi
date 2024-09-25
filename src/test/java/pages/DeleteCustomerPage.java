package pages;

import helper.HelpDeleteCustomer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DeleteCustomerPage extends BasePage {
    private static List<String> listName = new ArrayList<>();

    @FindBy(xpath = "//tr[@class=\"ng-scope\"]/td[1]")
    private List<WebElement> nameCustomers;
    @FindBy(xpath = "//button[@ng-click=\"deleteCust(cust)\"]")
    private List<WebElement> deletingButton;


    public DeleteCustomerPage() { //constructor
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public static List<String> getListName() {
        return listName;
    }

    public List<WebElement> getNameCustomers() {
        return nameCustomers;
    }

    /**
     * метод получает среднюю длину имен всех Customers
     * и удаляет всех Customer
     * длина имен которых ближе всего к средней
     *
     * @return возвращает текущую страницу
     */
    public DeleteCustomerPage deletingElement() {
        readListNameCustomers();
        double averageLengthName = HelpDeleteCustomer.getAverageLength();
        HelpDeleteCustomer.readTargetElementForDeleting(averageLengthName);
        deletingTargetElement(nameCustomers, HelpDeleteCustomer.getTargetName());
        return this;
    }

    /**
     * метод читает имена всех пользователей на странице
     * и заполняет ими лист, используется для вычисления
     * средней длины всех имен
     */
    public void readListNameCustomers() {

        for (WebElement el : nameCustomers) {
            listName.add(el.getText());
        }
    }

    /**
     * метод читает имена всех кастомеров на странице, сравнивает иж со списком
     * кастомеров подлежащих удалению и удаляет, при совподении
     *
     * @param nCustomer лист кастомеров
     * @param tName     лист кастомеров подлежащих удалению
     */
    public void deletingTargetElement(List<WebElement> nCustomer, List<String> tName) {
        for (int i = 0; i < nCustomer.size(); i++) {
            for (int k = 0; k < tName.size(); k++) {
                if (nCustomer.get(i).getText().equals(tName.get(k))) {
                    deletingButton.get(i).click();
                }
            }
        }
    }
}