package StringsAndNumbers;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
ТРЕБОВАНИЯ:
        Программа должна считывать данные с клавиатуры.
        Программа должна выводить данные на экран.
        Выведенные слова должны быть упорядочены по возрастанию (используй готовый метод isGreaterThan).
        Выведенные числа должны быть упорядочены по убыванию.
        Метод main должен использовать метод sort.
        Метод sort должен использовать метод isGreaterThan.
        Метод sort должен использовать метод isNumber.

        Проверь, что программа работает, если с клавиатуры были введены только слова. или только числа?!
        Количество чисел и слов может быть разным.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String line = reader.readLine();
        while (!line.isEmpty()) {
            list.add(line);
            line = reader.readLine();
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        String[] result = new String[array.length];
        List<Integer> stringsPositions = new ArrayList<>();
        List<Integer> numbersPositions = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                numbersPositions.add(i);
            } else {
                stringsPositions.add(i);
            }
        }

        System.out.println("string pos" + stringsPositions);
        System.out.println("number pos" + numbersPositions);

        if (stringsPositions.size() > 2) {
            int index = 0;
            while (index < stringsPositions.size()) {
                int minIndex = stringsPositions.get(index);
                for (int i = 1; (index + i) < stringsPositions.size(); i++) {
                    int curIndex = stringsPositions.get(index + i);
                    if (isGreaterThan(array[minIndex], array[curIndex])) {
                        String tmp = array[minIndex];
                        array[minIndex] = array[curIndex];
                        array[curIndex] = tmp;
                    }
                }
                index++;
            }
        }

        if (numbersPositions.size() > 2) {
            int index = 0;
            while (index < numbersPositions.size()) {
                int minIndex = numbersPositions.get(index);
                for (int i = 1; (index + i) < numbersPositions.size(); i++) {
                    int curIndex = numbersPositions.get(index + i);
//                    if (isGreaterThan(array[curIndex], )) {
                    if (Integer.parseInt(array[curIndex]) > Integer.parseInt(array[minIndex])) {
                        String tmp = array[minIndex];
                        array[minIndex] = array[curIndex];
                        array[curIndex] = tmp;
                    }
                }
                index++;
            }
        }
        // напишите тут ваш код
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String text) {
        if (text.length() == 0) {
            return false;
        }

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];

            // есть '-' внутри строки
            if (i != 0 && character == '-') {
                return false;
            }

            // не цифра и не начинается с '-'
            if (!Character.isDigit(character) && character != '-') {
                return false;
            }

            // одиночный '-'
            if (chars.length == 1 && character == '-') {
                return false;
            }
        }

        return true;
    }
}
