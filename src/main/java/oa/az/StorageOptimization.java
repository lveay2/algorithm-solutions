package oa.az;

import java.util.*;

public class StorageOptimization {

    /**
     * Put all rows of separator into a set and remove separators that belong to h. Do the same for columns of separators.
     * Loop through all the rest of separators and calculate the max difference between each. The result should be
     * the maxRowDiff * maxColDiff.
     *
     * Time complexity: O(n + m)
     * Space complexity: O(n + m)
     *
     * @param n
     * @param m
     * @param h
     * @param v
     * @return
     */
    public static long storage(int n, int m, List<Integer> h, List<Integer> v) {
        List<Integer> row =buildSeparator(n, h);
        List<Integer> col = buildSeparator(m, v);

        long maxRowDiff = 0, maxColDiff = 0;
        for (int i = 1; i < row.size(); i++) {
            maxRowDiff = Math.max(maxRowDiff, row.get(i) - row.get(i - 1));
        }
        for (int i = 1; i < col.size(); i++) {
            maxColDiff = Math.max(maxColDiff, col.get(i) - col.get(i - 1));
        }

        return maxRowDiff * maxColDiff;
    }

    private static List<Integer> buildSeparator(int n, List<Integer> h) {
        Set<Integer> separator = new TreeSet<>();
        for (int i = 0; i < n + 2; i++) {
            separator.add(i);
        }
        for (int r : h) {
            if (separator.contains(r)) {
                separator.remove(r);
            }
        }
        return new ArrayList<>(separator);
    }

    public static void main(String[] args) {
        System.out.println(StorageOptimization.storage(6, 6, Arrays.asList(4), Arrays.asList(2)));
        System.out.println(StorageOptimization.storage(3, 3, Arrays.asList(2), Arrays.asList(2)));
        System.out.println(StorageOptimization.storage(2, 2, Arrays.asList(1), Arrays.asList(2)));
        System.out.println(StorageOptimization.storage(3, 2, Arrays.asList(1, 2, 3), Arrays.asList(1, 2)));
    }

}
