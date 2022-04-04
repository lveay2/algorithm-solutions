package educative.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can
 * have duplicates, which means some numbers will be missing. Find all those missing numbers.
 *
 * <p>Example 1:
 *
 * <p>Input: [2, 3, 1, 8, 2, 3, 5, 1] Output: 4, 6, 7 Explanation: The array should have all numbers
 * from 1 to 8, due to duplicates 4, 6, and 7 are missing.
 *
 * <p>Example 2:
 *
 * <p>Input: [2, 4, 1, 2] Output: 3
 *
 * <p>Example 3:
 *
 * <p>Input: [2, 3, 2, 1] Output: 4
 */
public class AllMissingNumbers {

  public static List<Integer> findNumbers(int[] nums) {
    List<Integer> missingNumbers = new ArrayList<>();
    int i = 0;
    while (i < nums.length) {
      int j = nums[i] - 1;
      if (nums[i] != nums[j]) {
        swap(nums, i, j);
      } else {
        i++;
      }
    }

    // find the first number missing from its index, that will be our required number
    for (i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        missingNumbers.add(i + 1);
      }
    }

    return missingNumbers;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    List<Integer> missing = AllMissingNumbers.findNumbers(new int[] {2, 3, 1, 8, 2, 3, 5, 1});
    System.out.println("Missing numbers: " + missing);

    missing = AllMissingNumbers.findNumbers(new int[] {2, 4, 1, 2});
    System.out.println("Missing numbers: " + missing);

    missing = AllMissingNumbers.findNumbers(new int[] {2, 3, 2, 1});
    System.out.println("Missing numbers: " + missing);
  }
}
