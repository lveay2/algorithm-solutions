package oa.az.sub_strings_less_k_dist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubStringsLessKDist {

    public static List<String> subStringsLessKDist(String inputString, int num) {
        List<String> result = new ArrayList<>();

        if (num < 1 || inputString == null || inputString.length() == 0 || num > inputString.length()) return result;

        for (int i = 0; i + num <= inputString.length(); i++) {
            String subStr = inputString.substring(i, num + i);

            if (isMatch(subStr)) {
                result.add(subStr);
            }
        }

        return result;
    }

    private static boolean isMatch(String subStr) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : subStr.toCharArray()) {
            if (map.containsKey(c)) {
                int count = map.get(c);

                map.put(c, count + 1);

                if (map.get(c) > 2) {
                    return false;
                }
            } else {
                map.put(c, 1);
            }
        }

        int count = 0;

        for (int n : map.values()) {
            if (n == 2) {
                count++;
            }

            if (count > 1) return false;
        }

        return count == 1;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(subStringsLessKDist("democracy", 5));
        System.out.println(subStringsLessKDist("wawaglknagagwunagkwkwagl", 4));
    }

}
