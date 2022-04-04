package educative.sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring, which has all distinct characters.
 *
 * <p>Example 1:
 *
 * <p>Input: String="aabccbb"
 *
 * <p>Output: 3
 *
 * <p>Explanation: The longest substring with distinct characters is "abc".
 *
 * <p>Example 2:
 *
 * <p>Input: String="abbbb"
 *
 * <p>Output: 2
 *
 * <p>Explanation: The longest substring with distinct characters is "ab".
 *
 * <p>Example 3:
 *
 * <p>Input: String="abccde"
 *
 * <p>Output: 3
 *
 * <p>Explanation: Longest substrings with distinct characters are "abc" & "cde".
 */
public class LongestSubstringWithDistinctCharacters {

  public static int findLength(String str) {
    int start = 0, end = 0, max = 0;
    char left = str.charAt(start), right = str.charAt(end);
    Set<Character> set = new HashSet<>();
    while (end < str.length()) {
      right = str.charAt(end);

      while (set.contains(right)) {
        left = str.charAt(start);
        set.remove(left);
        start++;
      }

      set.add(right);
      //            System.out.println("set: " + set);

      max = Math.max(max, end - start + 1);

      end++;
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(
        "Length of the longest substring: "
            + LongestSubstringWithDistinctCharacters.findLength("aabccbb")
            + "==3");
    System.out.println(
        "Length of the longest substring: "
            + LongestSubstringWithDistinctCharacters.findLength("abbbb")
            + "==2");
    System.out.println(
        "Length of the longest substring: "
            + LongestSubstringWithDistinctCharacters.findLength("dvdf")
            + "==3");
    System.out.println(
        "Length of the longest substring: "
            + LongestSubstringWithDistinctCharacters.findLength("abccde")
            + "==3");
  }
}
