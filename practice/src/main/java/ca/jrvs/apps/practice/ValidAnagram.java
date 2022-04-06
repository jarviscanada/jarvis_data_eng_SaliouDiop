package ca.jrvs.apps.practice;

public class ValidAnagram {

    public boolean isValidAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        for(int i : s.toCharArray()){
            map[i - 'a']++;
        }
        for(int i : t.toCharArray()){
            map[i - 'a']--;
        }
        for(int i : map){
            if(i != 0){
                return false;
            }
        }
        return true;
    }
}
