package ca.jrvs.apps.practice;

public class Palindrome2 {
    // Given a string s, return true if the s can be palindrome after deleting at most one character from it.


    // Example 1:
    // Input: s = "abca"
    // Output: true
    // Explanation: We can delete the character 'c' from s to get a palindrome.

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
