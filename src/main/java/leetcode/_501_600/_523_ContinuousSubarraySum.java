package leetcode._501_600;

import java.util.HashMap;
import java.util.Map;

/*
523. Continuous Subarray Sum
Given an integer array nums and an integer k, return true if nums has
a continuous subarray of size at least two whose elements sum up to a
multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n such that
x = n * k. 0 is always a multiple of k.



Example 1:

Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
 */
public class _523_ContinuousSubarraySum {

  public static void main(String[] args) {
    System.out.println("true == " + checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6));
    System.out.println("true == " + checkSubarraySum(new int[] {0, 0}, 0));
    System.out.println("false == " + checkSubarraySum(new int[] {0, 1, 0}, 0));
  }

  public static boolean checkSubarraySum(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return false;
    }

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
