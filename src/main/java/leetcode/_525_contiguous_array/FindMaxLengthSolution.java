package leetcode._525_contiguous_array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * <p>
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class FindMaxLengthSolution {

    public static void main(String[] args) {
        System.out.println(new FindMaxLengthSolution().findMaxLength(new int[]{0, 1}));

        System.out.println(new FindMaxLengthSolution().findMaxLength(new int[]{0, 1, 0}));

        System.out.println(new FindMaxLengthSolution().findMaxLength(new int[]{0, 1, 0, 0, 1, 1, 0}));

        System.out.println(new FindMaxLengthSolution().findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
    }

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);

        int count = 0, max = 0;

        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);

            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }

        return max;
    }

}
