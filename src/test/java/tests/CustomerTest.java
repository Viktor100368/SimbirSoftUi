package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import helper.CreateCustomerPage;
import pages.DeleteCustomerPage;
import pages.SortingCustomersPage;


import java.util.Collections;
import java.util.List;


public class CustomerTest extends BaseTest {

    @Test
    @DisplayName("Create New Customer Test")
    @Description("Проверка функциональности создания нового кастомера")
    public void createNewCustomer() {
        page.startCreatingUsers().clickAddCustomerButton().creatingUser();
        Assert.assertEquals(CreateCustomerPage.getResult(), CreateCustomerPage.getFindingElement().getText());
    }

    @Test
    @DisplayName("Sorting Customer Test")
    @Description("Проверка функциональности сортировки")
    public void sortingCustomers() {
        List<String> names = page.startSorting().clickCustomerButton().sortingCustomers();
        Collections.sort(names);
        for (int i = 0; i < names.size(); i++) {
            Assert.assertEquals(names.get(i), SortingCustomersPage.getListName().get(i));
        }
    }

    @Test
    @DisplayName("Delete Customer Test")
    @Description("Проверка функциональности удаления кастомера")

    public void deleteCustomerWithAverageValueLength() {
        DeleteCustomerPage delPage = new DeleteCustomerPage();
        page.deleteCustomer().clickForDeleting().deletingElement();
        for (int i = 0; i < delPage.getNameCustomers().size(); i++) {
            for (int k = 0; k < delPage.getTargetName().size(); k++) {
                Assert.assertTrue(!(delPage.getNameCustomers().get(i).getText().equals(delPage.getTargetName().get(k))));
            }
        }
    }

}
