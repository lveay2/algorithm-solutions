package lintcode.presum;

import java.util.Arrays;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * 样例
 * Example1
 *
 * Input: [1,2,3,4]
 * Output: [24,12,8,6]
 * Explanation:
 * 2*3*4=24
 * 1*3*4=12
 * 1*2*4=8
 * 1*2*3=6
 *
 * Example2
 *
 * Input: [2,3,8]
 * Output: [24,16,6]
 * Explanation:
 * 3*8=24
 * 2*8=16
 * 2*3=6
 *
 * 挑战
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * 注意事项
 * Solve it without division and in O(n).
 */
public class _1310_productExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
//    System.out.println(Arrays.toString(res));

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println("[24, 12, 8, 6] == " + Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

}
