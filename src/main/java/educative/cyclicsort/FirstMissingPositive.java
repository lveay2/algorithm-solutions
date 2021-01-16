package educative.cyclicsort;

/**
 * Given an unsorted array containing numbers, find the smallest missing positive number in it.
 * <p>
 * Example 1:
 * <p>
 * Input: [-3, 1, 5, 4, 2] Output: 3 Explanation: The smallest missing positive number is '3'
 * <p>
 * Example 2:
 * <p>
 * Input: [3, -2, 0, 1, 2] Output: 4
 * <p>
 * Example 3:
 * <p>
 * Input: [3, 2, 5, 1] Output: 4
 */
public class FirstMissingPositive {

    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // swap to [1,2,3,0,-2]
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(FirstMissingPositive.findNumber(new int[]{-3, 1, 5, 4, 2}));
        System.out.println(FirstMissingPositive.findNumber(new int[]{3, -2, 0, 1, 2}));
        System.out.println(FirstMissingPositive.findNumber(new int[]{3, 2, 5, 1}));
    }

}
