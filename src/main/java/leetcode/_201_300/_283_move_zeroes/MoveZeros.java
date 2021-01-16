package leetcode._201_300._283_move_zeroes;

import java.util.Arrays;

public class MoveZeros {

    public static void main(String[] args) {
        MoveZeros mz = new MoveZeros();

        int[] nums1 = new int[]{0, 1, 0, 3, 12};

        mz.moveZeroes(nums1);

        int[] nums2 = new int[]{1, 0};

        mz.moveZeroes(nums2);

        int[] nums3 = new int[]{0, 1};

        mz.moveZeroes(nums3);

        int[] nums4 = new int[]{1};

        mz.moveZeroes(nums4);

        int[] nums5 = new int[]{2, 1};

        mz.moveZeroes(nums5);

        System.out.println("[1, 3, 12, 0, 0] == " + Arrays.toString(nums1));

        System.out.println("[1, 0] == " + Arrays.toString(nums2));

        System.out.println("[1, 0] == " + Arrays.toString(nums2));

        System.out.println("[1, 0] == " + Arrays.toString(nums3));

        System.out.println("[1] == " + Arrays.toString(nums4));

        System.out.println("[2, 1] == " + Arrays.toString(nums5));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i = 0;

        int j = 0;

        int l = nums.length;

        while (i < l && j < l) {
            if (nums[i] != 0) {
                nums[j++] = nums[i++];
            } else {
                i++;
            }
        }

        while (j < l) {
            nums[j++] = 0;
        }
    }

}
