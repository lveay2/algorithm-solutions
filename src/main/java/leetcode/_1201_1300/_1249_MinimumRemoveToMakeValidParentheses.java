package leetcode._1201_1300;

import java.util.Stack;

/*
1249. Minimum Remove to Make Valid Parentheses
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')',
in any positions ) so that the resulting parentheses string is valid
and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are
valid strings, or
It can be written as (A), where A is a valid string.


Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Constraints:
1 <= s.length <= 105
s[i] is either'(' , ')', or lowercase English letter.
 */
public class _1249_MinimumRemoveToMakeValidParentheses {

  public static void main(String[] args) {
    System.out.println("lee(t(c)o)de == " + minRemoveToMakeValid("lee(t(c)o)de)"));
    System.out.println("ab(c)d == " + minRemoveToMakeValid("a)b(c)d"));
    System.out.println(" == " + minRemoveToMakeValid("))(("));
  }

  public static String minRemoveToMakeValid(String s) {
    Stack<Integer> stack = new Stack<>();

    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (Character.isLetter(chars[i])) {
        continue;
      }
      if (chars[i] == '(') {
        stack.push(i);
      } else {
        if (stack.isEmpty()) {
          chars[i] = '*';
        } else {
          stack.pop();
        }
      }
    }

    while (!stack.isEmpty()) {
      chars[stack.pop()] = '*';
    }

    StringBuilder sb = new StringBuilder();
    for (char c : chars) {
      if (c != '*') {
        sb.append(c);
      }
    }

    return sb.toString();
  }
}
