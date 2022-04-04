package leetcode._1_100;

import java.util.ArrayList;
import java.util.List;

/*
22. Generate Parentheses
Given n pairs of parentheses, write a function to generate
all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
 */
public class _22_GenerateParentheses {

  static List<String> result = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("[((())), (()()), (())(), ()(()), ()()()] == \n" + generateParenthesis(3));
    System.out.println("[()] == " + generateParenthesis(1));
    System.out.println("[] == " + generateParenthesis(0));
  }

  public static List<String> generateParenthesis(int n) {
    result = new ArrayList<>();
    if (n == 0) {
      return new ArrayList<>();
    }
    backtrack(new StringBuilder(), n, n);
    return result;
  }

  private static void backtrack(StringBuilder combo, int left, int right) {
    if (left > right) {
      return;
    }
    if (left < 0 || right < 0) {
      return;
    }
    if (left == 0 && right == 0) {
      result.add(combo.toString());
      return;
    }

    combo.append("(");
    backtrack(combo, left - 1, right);
    combo.deleteCharAt(combo.length() - 1);

    combo.append(")");
    backtrack(combo, left, right - 1);
    combo.deleteCharAt(combo.length() - 1);
  }
}
