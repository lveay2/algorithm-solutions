package leetcode._601_700._680_valid_palindrome_ii;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a
 * palindrome.
 *
 * <p>Example 1: Input: "aba" Output: True
 *
 * <p>Example 2: Input: "abca" Output: True Explanation: You could delete the character 'c'.
 *
 * <p>Note: The string will only contain lowercase characters a-z. The maximum length of the string
 * is 50000.
 */
public class ValidPalindromeII {

  public static void main(String[] args) {
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("cbbcc"));
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("yd"));
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("aba"));
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("abca"));
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("acbca"));
    System.out.println("true == " + new ValidPalindromeII().validPalindrome("acbdca"));
    System.out.println(
        "true == "
            + new ValidPalindromeII()
                .validPalindrome(
                    "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
  }

  public boolean validPalindrome(String s) {
    if (s == null || s.trim().length() == 0) return false;

    if (s.trim().length() <= 2) return true;

    char[] array = s.toCharArray();

    int l = array.length;

    int i = 0, j = l - 1;

    boolean isFirstTime = true;

    while (i <= j) {
      if (i == j) break;

      char head = array[i];

      char tail = array[j];

      if (head == tail) {
        i++;
        j--;
      } else {
        int b = i + 1;

        int c = j - 1;

        if (b > c && isFirstTime) {
          return true;
        }

        if (isFirstTime) {
          isFirstTime = false;
        } else {
          return false;
        }

        if (array[b] == tail && array[b + 1] == array[c]) {
          i = b + 1;

          j--;
        } else if (head == array[c] && array[b] == array[c - 1]) {
          j = c - 1;

          i++;
        }
      }
    }

    return true;
  }
}
