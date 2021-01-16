package leetcode._1_100;

import java.util.HashMap;
import java.util.Map;

public class _3_Longest_Substring_Without_Repeating_Characters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = Integer.MIN_VALUE;
        for (int end = 0; end < s.length(); end++) {
            char ec = s.charAt(end);
            map.put(ec, map.getOrDefault(ec, 0) + 1);

            while (start <= end && map.get(ec) > 1) {
                char sc = s.charAt(start);
                map.put(sc, map.get(sc) - 1);
                start++;
            }

            max = Math.max(max, end - start + 1);
        }

        return max;
    }


    public static void main(String[] args) {
        System.out.println("1 == " + lengthOfLongestSubstring(" "));
        System.out.println("2 == " + lengthOfLongestSubstring("abba"));
        System.out.println("3 == " + lengthOfLongestSubstring("abcabcbb"));
    }

}
