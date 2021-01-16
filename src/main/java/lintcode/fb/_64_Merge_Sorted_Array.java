package lintcode.fb;

import java.util.Arrays;

public class _64_Merge_Sorted_Array {

    public static void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int indexA = m - 1;
        int indexB = n - 1;
        int i = m + n - 1;
        while (i >= 0 && indexA >= 0 && indexB >= 0) {
            if (A[indexA] > B[indexB]) {
                A[i] = A[indexA--];
            } else {
                A[i] = B[indexB--];
            }
            i--;
        }

        while (indexB >= 0) {
            A[i--] = B[indexB--];
        }
    }

    public static void main(String[] args) {
        int[] A = new int[5];
        A[0] = 1;
        A[1] = 2;
        A[2] = 5;
        mergeSortedArray(A, 3, new int[]{3, 4}, 2);
        System.out.println(Arrays.toString(A));
    }

}
