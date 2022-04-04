package leetcode._101_200;

import java.util.Arrays;
import java.util.List;

/*
139. Word Break
Given a string s and a dictionary of strings wordDict, return
true if s can be segmented into a space-separated sequence of
one or more dictionary words.

Note that the same word in the dictionary may be reused multiple
times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class _139_WordBreak {

  public static void main(String[] args) {
    System.out.println("true == " + wordBreak("leetcode", Arrays.asList("leet", "code")));
    System.out.println("true == " + wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    System.out.println(
        "false == " + wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    System.out.println(
        "false == " + wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    System.out.println(
        "false == "
            + wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    + "aaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList(
                    "a",
                    "aa",
                    "aaa",
                    "aaaa",
                    "aaaaa",
                    "aaaaaa",
                    "aaaaaaa",
                    "aaaaaaaa",
                    "aaaaaaaaa",
                    "aaaaaaaaaa")));
  }

  public static boolean wordBreak(String s, List<String> wordDict) {
    return dfs(s, wordDict, 0, new Boolean[s.length()]);
  }

  private static boolean dfs(String s, List<String> words, int start, Boolean[] memo) {
    if (start == s.length()) {
      return true;
    }

    // memo[start] store the result of dfs from s.substring(start)
    if (memo[start] != null) {
      return memo[start];
    }

    for (String word : words) {
      int size = word.length();
      int end = start + size;
      //            System.out.println(word + " start: " + start + " end:" + end + " "
      //                    + (end <= s.length() ? s.substring(start, end) : " ") + " "
      //                    + (end <= s.length() ? s.substring(end) : " "));
      //            System.out.println(Arrays.deepToString(memo));
      if (end <= s.length() && s.substring(start, end).equals(word)) {
        if (dfs(s, words, end, memo)) {
          memo[start] = true;
          //                    System.out.println(Arrays.deepToString(memo));
          return true;
        }
      }
    }
    memo[start] = false;
    //        System.out.println(Arrays.deepToString(memo));
    return false;
  }
}
