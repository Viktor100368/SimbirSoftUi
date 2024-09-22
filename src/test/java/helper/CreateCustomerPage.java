package helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateCustomerPage extends BasePage {
    private static String postCode = "";
    private static String result = "";

    public static String getResult() {
        return result;
    }

    public static WebElement getFindingElement() {
        return findingElement;
    }

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

    public CreateCustomerPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * метод заполняет поля формы и создает пользователя
     * @return возвращает текущую страницу
     */
    public CreateCustomerPage creatingUser() {
        generatePostCode();
        firstNameField.sendKeys(generateFirstName(postCode));
        lastNameField.sendKeys(generatedString());
        postCodeField.sendKeys(postCode);
        addNewCustomer.submit();
        driver.switchTo().alert().accept();
        customerButton.click();
        searchCustomer.sendKeys(result);
        return this;
    }

    public static String generatePostCode() {
        postCode = "";
        for (int i = 0; i < 10; i++) {
            postCode += (int) (Math.random() * 10);
        }
        return postCode;
    }

    public static String generateFirstName(String code) {
        result = "";

        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, (char) ('a' + i));
        }
        //разбиваем postCode по 2 символа
        String[] tmp = code.split("(?<=\\G.{" + 2 + "})");
        //генерируем имя
        for (int i = 0; i < tmp.length; i++) {
            int a = Integer.parseInt(tmp[i]) % 26;//приводим 2-значное значение к значению key в map
            if (i == 0) {
                result += Character.toUpperCase(map.get(a));
            } else {
                result += map.get(a);
            }
        }
        return result;
    }

    /**
     * метод генеригует строку из символов от a до z, которая используется для инициалиозации
     * поля lastNameField
     * @return возвращает страку из 7 символов
     */
    public static String generatedString() {
        int leftLimit = (int)'a';
        int rightLimit = (int)'z';
        int stirngLength = 7;
        Random random = new Random();
        String result = random.ints(leftLimit, rightLimit + 1)
                .limit(stirngLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return result;
    }

}
