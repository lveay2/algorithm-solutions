package lintcode.presum;

/**
 * Given an array with positive and negative numbers, find the maximum average subarray which length
 * should be greater or equal to given length k.
 *
 * <p>样例 Example 1:
 *
 * <p>Input: [1,12,-5,-6,50,3] 3 Output: 15.667 Explanation: (-6 + 50 + 3) / 3 = 15.667
 *
 * <p>Example 2:
 *
 * <p>Input: [5] 1 Output: 5.000 注意事项 It's guaranteed that the size of the array is greater or equal
 * to k.
 */
public class _617_maxAverage {

  public static double maxAverage(int[] nums, int k) {
    int l = nums.length;

    double start = nums[0], end = nums[0];
    for (int i = 0; i < l; i++) {
      start = Math.min(start, nums[i]);
      end = Math.max(end, nums[i]);
    }

    while (start + 1e-5 < end) {
      double mid = start + (end - start) / 2;

      if (findAvg(nums, mid, k)) {
        start = mid;
      } else {
        end = mid;
      }
    }

    return start;
  }

  private static boolean findAvg(int[] nums, double avg, int k) {
    double rightSum = 0;
    double leftSum = 0;
    double minLeftSum = 0;

    for (int i = 0; i < k; i++) {
      rightSum += nums[i] - avg;
    }

    for (int i = k; i <= nums.length; i++) {
      if (rightSum - minLeftSum >= 0) {
        return true;
      }

      if (i < nums.length) {
        rightSum += nums[i] - avg;
        leftSum += nums[i - k] - avg;
        minLeftSum = Math.min(minLeftSum, leftSum);
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(maxAverage(new int[] {1, 12, -5, -6, 50, 3}, 3));
  }
}
