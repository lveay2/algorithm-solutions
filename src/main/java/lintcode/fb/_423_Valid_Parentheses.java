package lintcode.fb;

import java.util.Stack;

public class _423_Valid_Parentheses {

    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if ("[{(".contains(String.valueOf(c))) {
                stack.push(c);
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValidParentheses("([)]"));
        System.out.println(isValidParentheses("()[]{}"));
        System.out.println(isValidParentheses("]"));
        System.out.println(isValidParentheses("(("));
    }

}
