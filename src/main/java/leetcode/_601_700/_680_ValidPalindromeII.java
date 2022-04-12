package leetcode._601_700;

/*
680. Valid Palindrome II
Given a string s, return true if the s can be palindrome after
deleting at most one character from it.

Example 1:
Input: s = "aba"
Output: true

Example 2:
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false

Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.
 */
public class _680_ValidPalindromeII {

  public static void main(String[] args) {
    System.out.println("true == " + validPalindrome("cbbcc"));
    System.out.println("true == " + validPalindrome("yd"));
    System.out.println("true == " + validPalindrome("aba"));
    System.out.println("true == " + validPalindrome("abca"));
    System.out.println("true == " + validPalindrome("acbca"));
    System.out.println("true == " + validPalindrome("acbdca"));
    System.out.println(
        "true == "
            + validPalindrome(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
  }

  public static boolean validPalindrome(String s) {
    int n = s.length();
    int left = 0, right = n - 1;

    while (left < right) {
//      String x = left + " " + right + " " + s.substring(left, right + 1);
      if (s.charAt(left) != s.charAt(right)) {
//        System.out.println(x);
        return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
      }
//      System.out.println(x);

      left++;
      right--;
    }

    return true;
  }

  private static boolean isPalindrome(String s, int left, int right) {
//    String x = left + " " + right + " " + s.substring(left, right + 1);
//    System.out.println("isPalindrome before: " + x);
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
//        System.out.println("isPalindrome while: " + x);
        return false;
      }

//      System.out.println("isPalindrome: " + x);
      left++;
      right--;
    }

    return true;
  }
}
