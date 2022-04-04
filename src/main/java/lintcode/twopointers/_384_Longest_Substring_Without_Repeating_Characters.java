package lintcode.twopointers;

public class _384_Longest_Substring_Without_Repeating_Characters {

  public static int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    int[] letters = new int[256];
    int max = Integer.MAX_VALUE;
    int i = 0;
    int n = s.length();
    for (int j = 0; j < n; j++) {
      char cj = s.charAt(j);
      letters[cj]++;

      while (i < n && letters[cj] > 1) {
        letters[s.charAt(i)]--;
        i++;
      }

      max = Math.max(max, j - i + 1);
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("abcabcbb"));
    System.out.println(lengthOfLongestSubstring("bbbbbb"));
    System.out.println(lengthOfLongestSubstring("z"));
    System.out.println(lengthOfLongestSubstring("aab"));
  }
}
