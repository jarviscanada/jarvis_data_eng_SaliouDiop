package ca.jrvs.apps.practice;

public class Palindrome {
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        str = str.toLowerCase();
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        StringBuilder sb = new StringBuilder(str);
        return str.equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
