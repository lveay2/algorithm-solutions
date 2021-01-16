package lintcode.twopointers;

import java.util.ArrayList;
import java.util.List;

public class _390_Find_Peak_Element_II {

    public static List<Integer> findPeakII(int[][] A) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = findRowPeak(A[i]);

            if (isValid(A, i, j)) {
                result.add(i);
                result.add(j);
                break;
            }
        }

        return result;
    }

    private static int findRowPeak(int[] row) {
        int l = 0, r = row.length - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;

            if (row[mid] > row[mid + 1]) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return row[l] > row[r] ? l : r;
    }

    private static boolean isValid(int[][] A, int i, int j) {
        if (0 < i && i < A.length - 1) {
            return A[i - 1][j] < A[i][j] && A[i][j] > A[i + 1][j];
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(findPeakII(new int[][]{
                {1, 2, 3, 6, 5},
                {16, 41, 23, 22, 6},
                {15, 17, 24, 21, 7},
                {14, 18, 19, 20, 10},
                {13, 14, 11, 10, 9},
        }));
    }

}
