package leetcode._101_200;

public class _152_Maximum_Product_Subarray {

    public static int maxProduct(int[] nums) {
        if (nums == null && nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int min_now = nums[0], max_now = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp_min = min_now;

            min_now = Math.min(nums[i], Math.min(nums[i] * min_now, nums[i] * max_now));
            max_now = Math.max(nums[i], Math.max(nums[i] * temp_min, nums[i] * max_now));

            result = Math.max(result, max_now);
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
        System.out.println(maxProduct(new int[]{2, -1, 1, 1}));
    }

}
