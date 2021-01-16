package educative.cyclicsort;

import java.util.Arrays;

/**
 * We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique
 * number from 1 to ‘n’ based on their creation sequence. This means that the object with sequence
 * number ‘3’ was created just before the object with sequence number ‘4’.
 * <p>
 * Write a function to sort the objects in-place on their creation sequence number in O(n)O(n) and
 * without any extra space. For simplicity, let’s assume we are passed an integer array containing
 * only the sequence numbers, though each number is actually an object.
 * <p>
 * Example 1:
 * <p>
 * Input: [3, 1, 5, 4, 2] Output: [1, 2, 3, 4, 5]
 * <p>
 * Example 2:
 * <p>
 * Input: [2, 6, 4, 3, 1, 5] Output: [1, 2, 3, 4, 5, 6]
 * <p>
 * Example 3:
 * <p>
 * Input: [1, 5, 6, 4, 3, 2] Output: [1, 2, 3, 4, 5, 6]
 */
public class CyclicSort {

    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] == nums[j]) {
                i++;
            } else {
                swap(nums, i, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 5, 4, 2};
        CyclicSort.sort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2, 6, 4, 3, 1, 5};
        CyclicSort.sort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 5, 6, 4, 3, 2};
        CyclicSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
