package ca.jrvs.apps.practice;

public class OnlyDigits {

    // check if a string contains only digits

    public static boolean isDigit(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // check if a string contains only digits

    public static boolean isDigit2(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.matches("[0-9]+");
    }

    // check if a string contains only digits

    public static boolean isDigit3(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits

    public static boolean isDigit4(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits

    public static boolean isDigit5(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits

    public static boolean isDigit6(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits

    public static boolean isDigit7(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits

    public static boolean isDigit8(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isDigit);
    }

    // check if a string contains only digits
}
