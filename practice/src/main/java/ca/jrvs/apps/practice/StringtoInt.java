package ca.jrvs.apps.practice;

public class StringtoInt {
    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int i = 0;
        int sign = 1;
        if (str.charAt(0) == '+') {
            i++;
        } else if (str.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        long result = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            i++;
        }
        return (int) (sign * result);
    }
}
