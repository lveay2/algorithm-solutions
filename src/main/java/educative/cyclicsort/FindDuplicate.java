package educative.cyclicsort;

/**
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. The array
 * has only one duplicate but it can be repeated multiple times. Find that duplicate number without
 * using any extra space. You are, however, allowed to modify the input array.
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 4, 4, 3, 2] Output: 4
 * <p>
 * Example 2:
 * <p>
 * Input: [2, 1, 3, 3, 5, 4] Output: 3
 * <p>
 * Example 3:
 * <p>
 * Input: [2, 4, 1, 4, 4] Output: 4
 */
public class FindDuplicate {

    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("4 == " + FindDuplicate.findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println("3 == " + FindDuplicate.findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println("4 == " + FindDuplicate.findNumber(new int[]{2, 4, 1, 4, 4}));
    }

}
