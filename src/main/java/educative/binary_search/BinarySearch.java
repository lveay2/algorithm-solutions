package educative.binary_search;

/**
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array. Though we
 * know that the array is sorted, we don’t know if it’s sorted in ascending or descending order. You
 * should assume that the array can have duplicates.
 *
 * <p>Write a function to return the index of the ‘key’ if it is present in the array, otherwise
 * return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: [4, 6, 10], key = 10 Output: 2
 *
 * <p>Example 2:
 *
 * <p>Input: [1, 2, 3, 4, 5, 6, 7], key = 5 Output: 4
 *
 * <p>Example 3:
 *
 * <p>Input: [10, 6, 4], key = 10 Output: 0
 *
 * <p>Example 4:
 *
 * <p>Input: [10, 6, 4], key = 4 Output: 2
 */
public class BinarySearch {

    public static int search(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end];
        while (start <= end) {
            // calculate the middle of the current range
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) return mid;

            if (isAscending) { // ascending order
                if (key < arr[mid]) {
                    end = mid - 1; // the 'key' can be in the first half
                } else { // key > arr[mid]
                    start = mid + 1; // the 'key' can be in the second half
                }
            } else { // descending order
                if (key > arr[mid]) {
                    end = mid - 1; // the 'key' can be in the first half
                } else { // key < arr[mid]
                    start = mid + 1; // the 'key' can be in the second half
                }
            }
        }
        return -1; // element not found
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 6, 10}, 10));
        System.out.println(search(new int[]{1, 2, 3, 4, 5, 6, 7}, 5));
        System.out.println(search(new int[]{10, 6, 4}, 10));
        System.out.println(search(new int[]{10, 6, 4}, 4));
    }

}
