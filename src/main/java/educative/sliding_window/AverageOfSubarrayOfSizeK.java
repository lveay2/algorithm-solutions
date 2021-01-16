package educative.sliding_window;

import java.util.Arrays;

/**
 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
 */
public class AverageOfSubarrayOfSizeK {

    public static double[] findAverages(int K, int[] arr) {
        int l = arr.length;
        double[] result = new double[l - K + 1];
        double winSum = 0;
        int startIndex = 0;
        for (int endIndex = 0; endIndex < l; endIndex++) {
            winSum += arr[endIndex];

            if (endIndex >= K - 1) {
                result[startIndex] = winSum / K;
                winSum -= arr[startIndex];
                startIndex++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        double[] result = AverageOfSubarrayOfSizeK
                .findAverages(5, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2});
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }

}
