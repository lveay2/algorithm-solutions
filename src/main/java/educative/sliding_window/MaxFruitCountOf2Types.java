package educative.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of characters where each character represents a fruit tree, you are given two
 * baskets, and your goal is to put maximum number of fruits in each basket. The only restriction is
 * that each basket can have only one type of fruit.
 * <p>
 * You can start with any tree, but you can’t skip a tree once you have started. You will pick one
 * fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third
 * fruit type.
 * <p>
 * Write a function to return the maximum number of fruits in both the baskets.
 * <p>
 * Example 1:
 * <p>
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 * <p>
 * Example 2:
 * <p>
 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
 * Output: 5
 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket. This can be done if we start with the
 * second letter: ['B', 'C', 'B', 'B', 'C']
 */
public class MaxFruitCountOf2Types {

    public static int findLength(char[] arr) {
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int l = arr.length;

        for (int end = 0; end < l; end++) {
            char c = arr[end];
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > 2) {
                char startC = arr[start];
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

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'A', 'C'}));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));
    }

}
