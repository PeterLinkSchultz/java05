package lab03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Divider {
    private final String string;
    private ArrayList<StringBuffer> splitted = new ArrayList<>();

    Divider(String string) {
        this.string = string;
    }

    public void dividingString() {
        int start = 0;

        while (start < string.length()) {
            int end = Math.min(string.length(), start + 3);

            StringBuffer substr = new StringBuffer(string.substring(start, end));
            splitted.add(substr);
            start = start + 3;
        }
    }

    public void replaceSymbol() {
        splitted.forEach((item) -> {
            int length = item.length();
            int left = item.charAt(0);
            int right = 0;

            if (length == 1) {
                return;
            }

            if (length == 3) {
                right = item.charAt(2);
            }

            item.setCharAt(1, getUniqueSymbol(left, right));
        });
    }

    public char getUniqueSymbol(int left, int right) {
        Random random = new Random();
        int newCharCode;

        do {
            newCharCode = random.nextInt(126 - 32) + 32;
        } while (newCharCode == left || newCharCode == right);

        return (char)newCharCode;
    }

    public void sort() {
        Collections.sort(splitted);
    }

    public void print() {
        splitted.forEach(System.out::println);
    }
}
