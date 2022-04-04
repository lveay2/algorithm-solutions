package educative.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct
 * characters.
 *
 * <p>Example 1:
 *
 * <p>Input: String="araaci", K=2 Output: 4 Explanation: The longest substring with no more than '2'
 * distinct characters is "araa." Example 2:
 *
 * <p>Input: String="araaci", K=1 Output: 2 Explanation: The longest substring with no more than '1'
 * distinct characters is "aa." Example 3:
 *
 * <p>Input: String="cbbebi", K=3 Output: 5 Explanation: The longest substrings with no more than
 * '3' distinct characters are "cbbeb" & "bbebi".
 */
public class LongestSubstringKDistinct {

  public static int findLength(String str, int k) {
    if (str == null || str.length() < k || k == 0) {
      throw new IllegalArgumentException();
    }

    int maxLength = 0;
    Map<Character, Integer> map = new HashMap<>();
    int start = 0;
    int l = str.length();

    for (int end = 0; end < l; end++) {
      char c = str.charAt(end);
      map.put(c, map.getOrDefault(c, 0) + 1);

      while (map.size() > k) {
        char startC = str.charAt(start);
        map.put(startC, map.get(startC) - 1);
        if (map.get(startC) == 0) {
          map.remove(startC);
        }
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  /**
   * Result Input Expected Output Actual Output Reason findLength(araaci, 2) 4 4 Succeeded
   * findLength(araaci, 1) 2 2 Succeeded findLength(cbbebi, 3) 5 5 Succeeded
   */
  public static void main(String[] args) {
    System.out.println(
        "Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
    System.out.println(
        "Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
    System.out.println(
        "Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
  }
}
