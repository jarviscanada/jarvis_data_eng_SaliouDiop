package ca.jrvs.apps.practice;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top == '(' && c != ')') {
                    return false;
                }
                if (top == '[' && c != ']') {
                    return false;
                }
                if (top == '{' && c != '}') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
