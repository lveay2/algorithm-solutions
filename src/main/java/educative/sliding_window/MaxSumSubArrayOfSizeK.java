package educative.sliding_window;

/**
 * Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any
 * contiguous subarray of size ‘k’.
 * <p>
 * Example 1:
 * <p>
 * Input: [2, 1, 5, 1, 3, 2], k=3 Output: 9 Explanation: Subarray with maximum sum is [5, 1, 3].
 * Example 2:
 * <p>
 * Input: [2, 3, 4, 1, 5], k=2 Output: 7 Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaxSumSubArrayOfSizeK {

    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0;
        int winSum = 0;
        int startIndex = 0;

        for (int end = 0; end < arr.length; end++) {
            winSum += arr[end];

            if (end >= k - 1) {
                maxSum = Math.max(maxSum, winSum);
                winSum -= arr[startIndex];
                startIndex++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[]{2, 1, 5, 1, 3, 2}));
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[]{2, 3, 4, 1, 5}));
    }

}
