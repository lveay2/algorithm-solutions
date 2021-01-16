package leetcode._301_400._303_range_sum_query_mutable;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 *
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
public class NumArray {

    private int[] sums;

    public NumArray(int[] nums) {
        int l = nums.length;

        if (l == 0) return;

        this.sums = new int[l + 1];

        for (int i = 0; i < l; i++) {
            // sum[i - 1] + nums[i]
            this.sums[i + 1] = this.sums[i] + nums[i];
        }
    }

    public static void main(String[] args) {
        NumArray obj = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});

        System.out.println("1 == " + obj.sumRange(0, 2));
        System.out.println("-1 == " + obj.sumRange(2, 5));
        System.out.println("-3 == " + obj.sumRange(0, 5));
    }

    public int sumRange(int i, int j) {
        if (this.sums == null) {
            return 0;
        }

        // sum[j] - sum[i-1]
        return this.sums[j + 1] - this.sums[i];
    }

}
