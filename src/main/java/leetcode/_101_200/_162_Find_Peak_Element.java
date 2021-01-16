package leetcode._101_200;

public class _162_Find_Peak_Element {

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > nums[mid] + 1) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return nums[l] > nums[r] ? l : r;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }

}
