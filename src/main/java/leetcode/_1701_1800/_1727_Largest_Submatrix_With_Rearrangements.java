package leetcode._1701_1800;

import java.util.Arrays;

public class _1727_Largest_Submatrix_With_Rearrangements {

  public static void main(String[] args) {
    _1727_Largest_Submatrix_With_Rearrangements solution =
        new _1727_Largest_Submatrix_With_Rearrangements();
    System.out.println(solution.largestSubmatrix(new int[][] {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}}));
    System.out.println(solution.largestSubmatrix(new int[][] {{1, 0, 1, 0, 1}}));
    System.out.println(solution.largestSubmatrix(new int[][] {{1, 1, 0}, {1, 0, 1}}));
    System.out.println(solution.largestSubmatrix(new int[][] {{0, 0}, {0, 0}}));
  }

  public int largestSubmatrix(int[][] matrix) {
    int rn = matrix.length;
    int cn = matrix[0].length;

    for (int i = 1; i < rn; i++) {
      for (int j = 0; j < cn; j++) {
        if (matrix[i][j] == 1) {
          matrix[i][j] += matrix[i - 1][j];
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for (int[] row : matrix) {
      Arrays.sort(row);
      for (int j = 0; j < cn; j++) {
        max = Math.max(max, row[j] * (cn - j));
      }
    }

    return max;
  }
}
