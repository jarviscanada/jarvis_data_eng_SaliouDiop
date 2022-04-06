package ca.jrvs.apps.practice;

public class RotatingString {
    public boolean rotateString(String s, String goal) {
        // if s can become goal after some number of rotations, return true
        // else return false

        if (s.length() != goal.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i) + s.substring(0, i);
            System.out.println(temp);
            if (temp.equals(goal)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RotatingString rs = new RotatingString();
        System.out.println(rs.rotateString("abcde", "cdeab"));

        String s = "abcde";
        String goal = "cdeab";
        System.out.println(s.substring(1) + s.charAt(0));
    }
}
