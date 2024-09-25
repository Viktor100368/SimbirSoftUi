package helper;

import pages.DeleteCustomerPage;

import java.util.ArrayList;
import java.util.List;

public class HelpDeleteCustomer {
    private static List<String> targetName = new ArrayList<>();

    public static List<String> getTargetName() {
        return targetName;
    }

    /**
     * вычисляет среднюю длину всех имен
     *
     * @return возвращает среднюю длнину
     */
    public static double getAverageLength() {
        double avg = DeleteCustomerPage.getListName().stream().map(x -> x.length())
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
    public static void readTargetElementForDeleting(double avg) {
        for (String s : DeleteCustomerPage.getListName()) {
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
}
