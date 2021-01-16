package leetcode._1_100._53_maximum_subarray;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.\
 */
public class MaxSubArraySolution {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int[] nums2 = new int[]{-1, -2, -3, -4};

        System.out.println(new MaxSubArraySolution().maxSubArray(nums));

        System.out.println(new MaxSubArraySolution().maxSubArray(nums2));

        System.out.println(new MaxSubArraySolution().maxSubArray(new int[0]));
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        int maxEndingHere = nums[0];

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);

            max = Math.max(maxEndingHere, max);
        }


        return max;
    }

}
