package helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HelpCreateCustomer {
    private static String postCode = "";
    private static String result = "";

    public static String getPostCode() {
        return postCode;
    }

    public static String getResult() {
        return result;
    }

    /**
     * метод генерирует postCode random значениями
     *
     * @return вазвращает postCode
     */
    public static String generatePostCode() {
        postCode = "";
        for (int i = 0; i < 10; i++) {
            postCode += (int) (Math.random() * 10);
        }
        return postCode;
    }

    /**
     * метод генерирует строку с именем пользователя на основе postCode и map
     *
     * @param code postCode
     * @return возвращаем строку с именем
     */
    public static String generateFirstName(String code) {
        result = "";
        //в цикле заполняем map: цифра, символ
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
                result += Character.toUpperCase(map.get(a));//если первый символ,
                // делаем заглавным (для упрощения сортировки)
            } else {
                result += map.get(a);
            }
        }
        return result;
    }

    /**
     * метод генеригует строку из символов от a до z, которая используется для инициалиозации
     * поля lastNameField
     *
     * @return возвращает строку из 7 символов
     */
    public static String generatedString() {
        int leftLimit = (int) 'a';
        int rightLimit = (int) 'z';
        int stringLength = 7;
        Random random = new Random();
        String result = random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return result;
    }
}
