package leetcode._301_400._304_range_sum_query_2d_immutable;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 * <p>
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
public class NumMatrix {

    private int[][] sums;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }

        int n = matrix[0].length;
        if (n == 0) {
            return;
        }

        this.sums = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // matrix[0][0], [i][j] = matrix[i][j] + matrix([0][0], [i -1][j]) + matrix([0][0], [i][j-1]) - matrix([0][0], [i-1][j-1])
                this.sums[i + 1][j + 1] = matrix[i][j] + this.sums[i][j + 1] + this.sums[i + 1][j] - this.sums[i][j];
            }
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix obj = new NumMatrix(matrix);

        System.out.println("8 == " + obj.sumRegion(2, 1, 4, 3));

        System.out.println("11 == " + obj.sumRegion(1, 1, 2, 2));

        System.out.println("12 == " + obj.sumRegion(1, 2, 2, 4));
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (this.sums == null) {
            return 0;
        }
        // matrix([row1][col1], [row2][col2]) = matrix([0][0], [row2][col2]) - matrix([0][0], [row2][col1-1])
        // - matrix([0][0], [row1 - 1][col2]) + matrix([0][0], [row1-1][col1-1])
        return this.sums[row2 + 1][col2 + 1] - this.sums[row2 + 1][col1] - this.sums[row1][col2 + 1] + this.sums[row1][col1];
    }

}
