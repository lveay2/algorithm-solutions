package leetcode._201_300;

import java.util.Arrays;

public class _283_Move_Zeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int index = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[index++] = n;
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        _283_Move_Zeroes solution = new _283_Move_Zeroes();
        int[] input = new int[]{0, 1, 0, 3, 12};
        solution.moveZeroes(input);
        System.out.println(Arrays.toString(input));
    }

}
