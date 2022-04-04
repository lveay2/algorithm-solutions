package lintcode.presum;

import java.util.HashMap;
import java.util.Map;

public class _1844_subarraySumEqualsKII {

  /**
   * Given an array of integers and an integer k, you need to find the minimum size of continuous
   * subarrays whose sum equals to k, and return its length.
   *
   * <p>if there are no such subarray, return -1.
   *
   * <p>样例 Example1
   *
   * <p>Input: nums = [1,1,1,2] and k = 3 Output: 2
   *
   * <p>Example2
   *
   * <p>Input: nums = [2,1,-1,4,2,-3] and k = 3 Output: 2
   *
   * <p>注意事项 the integer nums[i] may lower than 0
   */
  public static int subarraySumEqualsKII(int[] nums, int k) {
    int[] presums = getPresums(nums);

    Map<Integer, Integer> sum2Index = new HashMap<>();

    int min = Integer.MAX_VALUE;
    sum2Index.put(0, 0);
    for (int end = 0; end < nums.length; end++) {
      int targetSum = presums[end + 1] - k;
      if (sum2Index.containsKey(targetSum)) {
        int start = sum2Index.get(targetSum);
        int l = end - start + 1;
        min = Math.min(min, l);
      }
      sum2Index.put(presums[end + 1], end + 1);
    }

    return min;
  }

  private static int[] getPresums(int[] nums) {
    int[] presums = new int[nums.length + 1];
    presums[0] = 0;
    for (int i = 0; i < nums.length; i++) {
      presums[i + 1] = nums[i] + presums[i];
    }

    return presums;
  }

  public static void main(String[] args) {
    System.out.println("2 == " + subarraySumEqualsKII(new int[] {1, 1, 1, 2}, 3));
    System.out.println("2 == " + subarraySumEqualsKII(new int[] {2, 1, -1, 4, 2, -3}, 3));
  }
}
