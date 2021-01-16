package leetcode._523_continuous_subarray_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 * <p>
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * <p>
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * <p>
 * Note:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class CheckSubarraySum {

    public static void main(String[] args) {
        System.out.println("true == " + new CheckSubarraySum().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));

        System.out.println("true == " + new CheckSubarraySum().checkSubarraySum(new int[]{0, 0}, 0));

        System.out.println("false == " + new CheckSubarraySum().checkSubarraySum(new int[]{0, 1, 0}, 0));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        // creat sums map
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int presum = 0;

        for (int i = 0; i < nums.length; i++) {
            presum += nums[i];

            if (k != 0) {
                presum %= k;
            }

            Integer index = map.get(presum);

            if (index != null) {
                if (i - index > 1) {
                    return true;
                }
            } else {
                map.put(presum, i);
            }

//            if (index == null) {
//                map.put(presum, i);
//
//                continue;
//            }
//
//            if (i - index > 1) {
//                return true;
//            }
        }

        return false;
    }

}
