package leetcode._438_find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * <p>Strings consists of lowercase English letters only and the length of both strings s and p will
 * not be larger than 20,100.
 *
 * <p>The order of output does not matter.
 *
 * <p>Example 1:
 *
 * <p>Input: s: "cbaebabacd" p: "abc"
 *
 * <p>Output: [0, 6]
 *
 * <p>Explanation: The substring with start index = 0 is "cba", which is an anagram of "abc". The
 * substring with start index = 6 is "bac", which is an anagram of "abc". Example 2:
 *
 * <p>Input: s: "abab" p: "ab"
 *
 * <p>Output: [0, 1, 2]
 *
 * <p>Explanation: The substring with start index = 0 is "ab", which is an anagram of "ab". The
 * substring with start index = 1 is "ba", which is an anagram of "ab". The substring with start
 * index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAnagrams {

  public static void main(String[] args) {
    System.out.println("[0, 6] == " + new FindAnagrams().findAnagrams("cbaebabacd", "abc"));
    System.out.println("[0, 1, 2] == " + new FindAnagrams().findAnagrams("abab", "ab"));
  }

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();

    if (s == null || p == null || s.length() == 0 || p.length() == 0) return result;

    if (p.length() > s.length()) return result;

    Map<Character, Integer> map = new HashMap<>();

    for (char c : p.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int start = 0, end = 0;

    int count = map.size();

    while (end < s.length()) {
      char c = s.charAt(end);

      if (map.containsKey(c)) {
        map.put(c, map.get(c) - 1);
        if (map.get(c) == 0) count--;
      }

      while (count == 0) {
        char startC = s.charAt(start);

        if (map.containsKey(startC)) {
          map.put(startC, map.get(startC) + 1);

          if (map.get(startC) > 0) {
            count++;
          }
        }

        if (end - start + 1 == p.length()) {
          result.add(start);
        }

        start++;
      }

      end++;
    }

    return result;
  }
}
