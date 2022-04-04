package leetcode._201_300;

import java.util.ArrayList;
import java.util.List;

/*
241. Different Ways to Add Parentheses

Given a string expression of numbers and operators, return all possible
results from computing all the different possible ways to group numbers
and operators. You may return the answer in any order.


Example 1:
Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2

Example 2:
Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

Constraints:
1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
 */
public class _241_DifferentWaysToAddParentheses {

  public static void main(String[] args) {
    System.out.println("[0, 2] == " + diffWaysToCompute("2-1-1"));
    System.out.println("[-34,-14,-10,-10,10] == " + diffWaysToCompute("2*3-4*5"));
  }

  public static List<Integer> diffWaysToCompute(String expression) {
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '+' || c == '-' || c == '*') {
        List<Integer> left = diffWaysToCompute(expression.substring(0, i));
        List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

        for (int num1 : left) {
          for (int num2 : right) {
            if (c == '+') {
              ans.add(num1 + num2);
            } else if (c == '-') {
              ans.add(num1 - num2);
            } else if (c == '*') {
              ans.add(num1 * num2);
            }
          }
        }
      }
    }

    if (ans.isEmpty()) {
      ans.add(Integer.parseInt(expression));
    }

    return ans;
  }
}
