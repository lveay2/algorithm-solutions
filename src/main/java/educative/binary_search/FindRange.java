package educative.binary_search;

/**
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’. The
 * range of the ‘key’ will be the first and last position of the ‘key’ in the array.
 *
 * <p>Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1,
 * -1].
 *
 * <p>Example 1:
 *
 * <p>Input: [4, 6, 6, 6, 9], key = 6 Output: [1, 3]
 *
 * <p>Example 2:
 *
 * <p>Input: [1, 3, 8, 10, 15], key = 10 Output: [3, 3]
 *
 * <p>Example 3:
 *
 * <p>Input: [1, 3, 8, 10, 15], key = 12 Output: [-1, -1]
 */
public class FindRange {

    public static int[] findRange(int[] arr, int key) {
        int left = search(arr, key, true);

        int right = -1;
        if (left != -1) {
            right = search(arr, key, false);
        }

        return new int[]{left, right};
    }

    private static int search(int[] arr, int key, boolean findMin) {
        int start = 0, end = arr.length - 1;

        if (key == arr[start] && findMin) return start;
        if (key == arr[end] && !findMin) return end;

        int keyIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key < arr[mid]) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                keyIndex = mid;
                if (findMin) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return keyIndex;
    }

    public static void main(String[] args) {
        int[] result = FindRange.findRange(new int[]{4, 6, 6, 6, 9}, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[]{1, 3, 8, 10, 15}, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[]{1, 3, 8, 10, 15}, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }

}
