package leetcode._1_100;

import java.util.*;

/*
49. Group Anagrams
Given an array of strings strs, group the anagrams together. You can
return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of
a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
public class _49_GroupAnagrams {

  public static void main(String[] args) {
    System.out.println("[[EAT], [tan, nat], [bat], [eat, tea, ate]] ==\n" + groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat", "EAT"}));
    System.out.println("[[]] == " + groupAnagrams(new String[] {""}));
    System.out.println("[[a]] == " + groupAnagrams(new String[] {"a"}));
    System.out.println("[[b], [a], [c]] == " + groupAnagrams(new String[] {"a","b","c"}));
    System.out.println("[[tea, tea]] == " + groupAnagrams(new String[] {"tea", "tea"}));
  }

  public static List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
      char[] chars = new char[128];
      for (char c : s.toCharArray()) {
        chars[c - '\0']++;
      }

      String code = String.valueOf(chars);
      map.putIfAbsent(code, new ArrayList<>());
      map.get(code).add(s);
    }

    return new ArrayList<>(map.values());
  }
}
