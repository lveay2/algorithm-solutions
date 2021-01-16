package lintcode.presum;

import java.util.*;

/**
 * Given a string str and a dictionary dict, you need to find out which words in the dictionary are subsequences of the string and return those words.The order of the words returned should be the same as the order in the dictionary.
 *
 * 样例
 * Example 1:
 *
 * Input:
 * str="bcogtadsjofisdhklasdj"
 * dict=["book","code","tag"]
 * Output:
 * ["book"]
 * Explanation:Only book is a subsequence of str
 *
 * Example 2:
 *
 * Input:
 * str="nmownhiterer"
 * dict=["nowhere","monitor","moniter"]
 * Output:
 * ["nowhere","moniter"]
 * 挑战
 * |str|<=100000
 *
 * 注意事项
 * |str|<=1000
 * the sum of all words length in dictionary<=1000
 * (All characters are in lowercase)
 */
public class _194_findWords {

    public static List<String> findWords(String str, List<String> dict) {
        List<String> result = new ArrayList<>();
        Map<Character, List<Integer>> char2IndexPosition = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            List<Integer> indexPositions = char2IndexPosition
                    .getOrDefault(str.charAt(i), new ArrayList<>());
            indexPositions.add(i);
            char2IndexPosition.put(str.charAt(i), indexPositions);
        }

        for (String s : dict) {
            char[] c = s.toCharArray();
            boolean found = true;

            int currentIndex = 0;
            for (int i = 0; i < c.length; i++) {
                if (!char2IndexPosition.containsKey(c[i])) {
                    found = false;
                    break;
                }

                currentIndex = findNextPosition(char2IndexPosition.get(c[i]), currentIndex);
                if (currentIndex == -1) {
                    found = false;
                    break;
                }

                currentIndex++;
            }

            if (found) {
                result.add(s);
            }
        }

        return result;
    }

    private static int findNextPosition(List<Integer> indexList, int currentIndex) {
        int left = 0;
        int right = indexList.size() - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (currentIndex < indexList.get(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (indexList.get(left) >= currentIndex) {
            return indexList.get(left);
        }

        if (indexList.get(right) >= currentIndex) {
            return indexList.get(right);
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findWords("bcogtadsjofisdhklasdj", Arrays.asList("book", "code", "tag")));
        System.out.println(findWords("nmownhiterer", Arrays.asList("nowhere", "monitor", "moniter")));
    }

}
