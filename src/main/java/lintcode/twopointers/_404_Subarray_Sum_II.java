package lintcode.twopointers;

public class _404_Subarray_Sum_II {

    public static int subarraySumII(int[] A, int start, int end) {
        int result = 0;
        if (A == null || A.length == 0) {
            return result;
        }

        int n = A.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + A[i - 1];
        }

        int l = 0, r = 0;
        for (int i = 1; i <= n; i++) {
            // move l index while sum[i] - sums[l] > end if find the postion where
            while (l < i && sums[i] - sums[l] > end) {
                l++;
            }

            // move r index while sum[i] - sums[l] > end
            while (r < i && sums[i] - sums[r] >= start) {
                r++;
            }

            result += r - l;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(subarraySumII(new int[]{1, 2, 3, 4}, 1, 3));
        System.out.println(subarraySumII(new int[]{1, 2, 3, 4}, 1, 100));
    }

}
