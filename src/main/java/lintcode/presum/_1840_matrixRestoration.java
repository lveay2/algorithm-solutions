package lintcode.presum;

import java.util.Arrays;

/**
 * There is a matrix beforebefore with nn rows and mm columns. For each element in before
 * before[i][j]before[i][j], we will use the following algorithm to convert it to after [i]
 * [j]after[i][j]. Given the afterafter matrix, please restore the original matrix beforebefore.
 *
 * <p>s = 0 for i1: 0 -> i for j1: 0 -> j s = s + before[i1][j1] after[i][j] = s 样例 注意事项 n,m \leq
 * 1\,000n,m≤1000
 */
public class _1840_matrixRestoration {

  public static int[][] matrixRestoration(int n, int m, int[][] after) {

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (i > 0) {
          after[i][j] -= after[i - 1][j];
        }
        if (j > 0) {
          after[i][j] -= after[i][j - 1];
        }
        if (i > 0 && j > 0) {
          after[i][j] += after[i - 1][j - 1];
        }
      }
    }

    return after;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.deepToString(matrixRestoration(2, 2, new int[][] {{1, 3}, {4, 10}})));
  }
}
