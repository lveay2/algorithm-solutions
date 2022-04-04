package leetcode._1_100._15_three_sum;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * <p>Note:
 *
 * <p>The solution set must not contain duplicate triplets.
 *
 * <p>Example:
 *
 * <p>Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * <p>A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum {

  public static void main(String[] args) {
    System.out.println(new ThreeSum().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));

    System.out.println(new ThreeSum().threeSum(new int[] {0, 0, 0}));
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> result = new HashSet<>();

    if (nums == null || nums.length == 0) return new ArrayList<>(result);

    int l = nums.length;

    Arrays.sort(nums);

    for (int i = 0; i < l - 2; i++) {
      int low = i + 1, high = l - 1, sum = 0 - nums[i];

      while (low < high) {
        if (nums[low] + nums[high] == sum) {
          result.add(Arrays.asList(nums[i], nums[low], nums[high]));

          while (low < high && nums[low] == nums[low + 1]) low++;

          while (low < high && nums[high] == nums[high - 1]) high--;

          low++;

          high--;
        } else if (nums[low] + nums[high] < sum) {
          low++;
        } else {
          high--;
        }
      }
    }

    return new ArrayList<>(result);
  }
}
