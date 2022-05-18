package ca.jrvs.apps.practice;

import java.math.BigDecimal;

public class StringtoInteger {

    public static int atoi(String text) {
        int result = 0;
        int sign = 1;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        int i = 0;
        while (i < text.length() && text.charAt(i) == ' ') {
            i++;
        }
        if (i < text.length() && text.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < text.length() && text.charAt(i) == '+') {
            i++;
        }
        while (i < text.length() && Character.isDigit(text.charAt(i))) {
            result = result * 10 + (text.charAt(i) - '0');
            i++;
        }
        if (result > max - 1) {
           return sign == 1 ? max : min;
        }
        return (result * sign);
    }

    public static void main(String[] args) {
        String text = "-912834722";
        System.out.println(atoi(text));
    }
}
