package lintcode.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _107_Word_Break {

  public static boolean wordBreak3(String s, Set<String> wordSet) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && wordSet.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  // jiu zhang
  public static boolean wordBreak2(String s, Set<String> wordSet) {
    if (s == null) {
      return true;
    }

    int max = 0;

    for (String str : wordSet) {
      max = Math.max(max, str.length());
    }

    int l = s.length();
    boolean[] dp = new boolean[l + 1];
    dp[0] = true;

    for (int i = 1; i <= l; i++) {
      for (int j = 1; j <= max; j++) {
        if (i < j) {
          break;
        }

        if (!dp[i - j]) {
          continue;
        }

        String word = s.substring(i - j, i);
        if (wordSet.contains(word)) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[l];
  }

  public static boolean wordBreak(String s, Set<String> wordSet) {
    return dfs(s, wordSet, 0);
  }

  private static boolean dfs(String s, Set<String> dict, int length) {
    if (length == s.length()) {
      return true;
    }

    for (int i = 1; i + length <= s.length(); i++) {
      if (dict.contains(s.substring(length, length + i)) && dfs(s, dict, length + i)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(wordBreak("a", new HashSet<>(Arrays.asList("a"))));
    System.out.println(wordBreak("lintcode", new HashSet<>(Arrays.asList("lint", "code"))));
    System.out.println(wordBreak2("a", new HashSet<>(Arrays.asList("a"))));
    System.out.println(wordBreak2("lintcode", new HashSet<>(Arrays.asList("lint", "code"))));
    System.out.println(wordBreak2("lintcode", new HashSet<>(Arrays.asList("lin", "ode", "tc"))));
    System.out.println(wordBreak3("a", new HashSet<>(Arrays.asList("a"))));
    System.out.println(wordBreak3("lintcode", new HashSet<>(Arrays.asList("lint", "code"))));
    System.out.println(wordBreak3("lintcode", new HashSet<>(Arrays.asList("lin", "ode", "tc"))));
  }
}
