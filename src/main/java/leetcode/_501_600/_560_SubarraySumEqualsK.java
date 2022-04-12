package leetcode._501_600;

import java.util.HashMap;
import java.util.Map;

/*
560. Subarray Sum Equals K
Given an array of integers nums and an integer k, return the total
number of subarrays whose sum equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class _560_SubarraySumEqualsK {

  public static void main(String[] args) {
    System.out.println("2 == " + subarraySum(new int[] {1, 1, 1}, 2));
    System.out.println("2 == " + subarraySum(new int[] {1, 2, 3}, 3));
    System.out.println("4 == " + subarraySum(new int[] {1, 2, 1, 2, 1}, 3));
  }

  static int count = 0;

  public static int subarraySum(int[] nums, int k) {
    int n = nums.length;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int ans = 0, sum_i = 0;
    for (int i = 0; i < n; i++) {
      sum_i += nums[i];
      int sum_j = sum_i - k;

      if (map.containsKey(sum_j)) {
        ans += map.get(sum_j);
      }

      map.put(sum_i, map.getOrDefault(sum_i, 0) + 1);
    }

    return ans;
  }
}
