package leetcode._401_500;

import leetcode._438_find_all_anagrams_in_a_string.FindAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
438. Find All Anagrams in a String
Given two strings s and p, return an array of all the start indices
of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of
a different word or phrase, typically using all the original letters
exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:
1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */
public class _438_FindAllAnagramsInAString {

    public static void main(String[] args) {
        System.out.println("[0, 6] == " + new FindAnagrams().findAnagrams("cbaebabacd", "abc"));
        System.out.println("[0, 1, 2] == " + new FindAnagrams().findAnagrams("abab", "ab"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return result;
        }

        if (p.length() > s.length()) {
            return result;
        }

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
