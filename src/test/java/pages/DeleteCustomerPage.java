package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DeleteCustomerPage extends BasePage {
    private List<String> listName = new ArrayList<>();
    private List<String> targetName = new ArrayList<>();

    public List<String> getTargetName() {
        return targetName;
    }

    @FindBy(xpath = "//tr[@class=\"ng-scope\"]/td[1]")
    private List<WebElement> nameCustomers;

    public List<WebElement> getNameCustomers() {
        return nameCustomers;
    }

    @FindBy(xpath = "//button[@ng-click=\"deleteCust(cust)\"]")
    private List<WebElement> deletingButton;

    public DeleteCustomerPage() {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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
        double averageLengthName = getAverageLength();
        readTargetElementForDeleting(averageLengthName);
        deletingTargetElement(nameCustomers, targetName);
        return this;
    }


    public void readListNameCustomers() {

        for (WebElement el : nameCustomers) {
            listName.add(el.getText());
        }
    }

    /**
     * вычисляет среднюю длину всех имен
     *
     * @return возвращает среднюю длнину
     */
    public double getAverageLength() {
        double avg = listName.stream().map(x -> x.length())
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
        return avg;
    }

    /**
     * метод читает имена клиентов сравнивает длину имени со средней длиной всех имен
     * и заполняет лист на удаление
     *
     * @param avg среднее значение длин всех имен
     */
    public void readTargetElementForDeleting(double avg) {
        for (String s : listName) {
            if (s.length() < avg) {
                if (avg - s.length() > 0 && avg - s.length() <= 1) {
                    targetName.add(s);
                }
            } else {
                if (s.length() - avg > 0 && s.length() - avg <= 1) {
                    targetName.add(s);
                }
            }
        }
    }

    /**
     * метод читает имена всех кастомеров на странице, сравнивает иж со списком
     * кастомеров подлежащих удалению и удаляет при совподению
     *
     * @param nCustomer лист кастомеров
     * @param tName     лист кастомеров подлежащих удалению
     */
    public void deletingTargetElement(List<WebElement> nCustomer, List<String> tName) {
        for (int i = 0; i < nCustomer.size(); i++) {
            for (int k = 0; k < tName.size(); k++)
                if (nCustomer.get(i).getText().equals(tName.get(k))) {
                    deletingButton.get(i).click();
                }
        }
    }

}