package educative.binary_search;

/**
 * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
 * The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to
 * the ‘key’.
 *
 * <p>Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling
 * return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: [4, 6, 10], key = 6 Output: 1 Explanation: The smallest number greater than or equal to
 * '6' is '6' having index '1'.
 *
 * <p>Example 2:
 *
 * <p>Input: [1, 3, 8, 10, 15], key = 12 Output: 4 Explanation: The smallest number greater than or
 * equal to '12' is '15' having index '4'.
 *
 * <p>Example 3:
 *
 * <p>Input: [4, 6, 10], key = 17 Output: -1 Explanation: There is no number greater than or equal
 * to '17' in the given array.
 */
public class CeilingOfANumber {

  public static int searchCeilingOfANumber(int[] arr, int key) {
    if (key > arr[arr.length - 1]) // if the 'key' is bigger than the biggest element
    return -1;

    int start = 0, end = arr.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (key < arr[mid]) {
        end = mid - 1;
      } else if (key > arr[mid]) {
        start = mid + 1;
      } else { // found the key
        return mid;
      }
    }
    // since the loop is running until 'start < end', so at the end of the while loop, 'start + 1 ==
    // end'
    // we are not able to find the element in the given array, so the next big number will be
    // arr[end]
    return end;
  }

  public static void main(String[] args) {
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] {4, 6, 10}, 6));
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] {1, 3, 8, 10, 15}, 12));
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] {4, 6, 10}, 17));
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] {4, 6, 10}, -1));
  }
}
