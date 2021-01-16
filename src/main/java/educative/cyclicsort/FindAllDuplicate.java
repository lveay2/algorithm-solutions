package educative.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. The array
 * has some numbers appearing twice, find all these duplicate numbers without using any extra
 * space.
 * <p>
 * Example 1:
 * <p>
 * Input: [3, 4, 4, 5, 5] Output: [4, 5]
 * <p>
 * Example 2:
 * <p>
 * Input: [5, 4, 7, 2, 3, 5, 3] Output: [3, 5]
 */
public class FindAllDuplicate {

    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        List<Integer> duplicateNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                duplicateNumbers.add(nums[i]);
            }
        }

        return duplicateNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = FindAllDuplicate.findNumbers(new int[]{3, 4, 4, 5, 5});
        System.out.println("Duplicates are: " + duplicates);

        duplicates = FindAllDuplicate.findNumbers(new int[]{5, 4, 7, 2, 3, 5, 3});
        System.out.println("Duplicates are: " + duplicates);
    }

}
