package lintcode.twopointers;

public class _1850_Pick_Apples {

    public static int pickApples(int[] A, int K, int L) {
        int n = A.length;

        int maxApples = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int leftMaxK = pickMax(A, K, 0, i);
            int rightMaxL = pickMax(A, L, i, n);
            int leftMaxL = pickMax(A, L, 0, i);
            int rightMaxK = pickMax(A, K, i, n);

            if (leftMaxK != -1 && rightMaxL != -1) {
                maxApples = Math.max(maxApples, leftMaxK + rightMaxL);
            }
            if (leftMaxL != -1 && rightMaxK != -1) {
                maxApples = Math.max(maxApples, leftMaxL + rightMaxK);
            }
        }

        return maxApples == Integer.MIN_VALUE ? -1 : maxApples;
    }

    private static int pickMax(int[] A, int k, int start, int end) {
        if (k > end - start) {
            return -1;
        }

        int apples = 0;
        for (int i = start; i < start + k; i++) {
            apples += A[i];
        }

        int maxApples = apples;
        int left = start, right = start + k;
        while (right < end) {
            apples -= A[left];
            apples += A[right];
            maxApples = Math.max(maxApples, apples);

            left++;
            right++;
        }

        return maxApples;
    }

    public static void main(String[] args) {
        System.out.println(pickApples(new int[]{6, 1, 4, 6, 3, 2, 7, 4}, 3, 2));
    }

}
