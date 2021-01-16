package educative.cyclicsort;

/**
 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the
 * array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 0, 3, 1]
 * <p>
 * Output: 2 Example 2:
 * <p>
 * Input: [8, 3, 5, 2, 4, 6, 0, 1] Output: 7
 */
public class MissingNumber {

    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i];
            if (nums[i] < nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        // find the first number missing from its index, that will be our required number
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("2 == " + MissingNumber.findMissingNumber(new int[]{4, 0, 3, 1}));
        System.out
                .println("7 == " + MissingNumber.findMissingNumber(new int[]{8, 3, 5, 2, 4, 6, 0, 1}));
    }

}
