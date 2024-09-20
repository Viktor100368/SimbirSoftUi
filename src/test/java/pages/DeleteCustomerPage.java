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

    public DeleteCustomerPage deletingElement() {
        getListNameCustomers();
        double d = getAverageLength();
        getTargetElementForDeleting(d);
        deletingTargetElement(nameCustomers, targetName);
        return this;
    }


    public void getListNameCustomers() {
        for (WebElement el : nameCustomers) {
            listName.add(el.getText());
        }
    }

    public double getAverageLength() {
        double avg = listName.stream().map(x -> x.length())
                .mapToDouble(Integer::doubleValue)
                .average().orElse(0.0);
        return avg;
    }

    public void getTargetElementForDeleting(double d) {
        for (String s : listName) {
            if (s.length() < d) {
                if (d - s.length() > 0 && d - s.length() <= 1) {
                    targetName.add(s);
                }
            } else {
                if (s.length() - d > 0 && s.length() - d <= 1) {
                    targetName.add(s);
                }
            }
        }
    }

    public void deletingTargetElement(List<WebElement> nCustomer, List<String> tName) {
        for (int i = 0; i < nCustomer.size(); i++) {
            for (int k = 0; k < tName.size(); k++)
                if (nCustomer.get(i).getText().equals(tName.get(k))) {
                    deletingButton.get(i).click();
                }
        }
    }

}