package lintcode.system_design;

public class _479_Second_Max_of_Array {

    public static int secondMax(int[] nums) {
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > firstMax) {
                secondMax = firstMax;
                firstMax = num;
            } else if (num > secondMax) {
                secondMax = num;
            }
        }

        return secondMax;
    }


    public static void main(String[] args) {
        System.out.println(secondMax(new int[]{1, 3, 2, 4}));
        System.out.println(secondMax(new int[]{1, -1, -2}));
        System.out.println(secondMax(new int[]{1, 1, 2, 2}));
    }

}
