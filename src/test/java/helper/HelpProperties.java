package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class HelpProperties {
    public static void createProperty() {

        Properties properties = new Properties();
        properties.setProperty("url", "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        try (
                FileOutputStream fos = new FileOutputStream("./src/test/resources/local.properties")) {
            properties.store(fos, "local property");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getProperty(Properties properties, String key) {
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
