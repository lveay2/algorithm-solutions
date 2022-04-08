package leetcode._1401_1500;

import java.util.Arrays;
import java.util.List;

/*
1428. Leftmost Column with at Least a One
A row-sorted binary matrix means that all elements are 0 or 1 and
each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index
(0-indexed) of the leftmost column with a 1 in it. If such an
index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access
the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at
index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix
as a list of 2 elements [rows, cols], which means the matrix is
rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will
be judged Wrong Answer. Also, any solutions that attempt to circumvent
the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary
matrix mat. You will not have access to the binary matrix directly.

Example 1:
Input: mat = [[0,0],[1,1]]
Output: 0

Example 2:
Input: mat = [[0,0],[0,1]]
Output: 1

Example 3:
Input: mat = [[0,0],[0,0]]
Output: -1

Constraints:
rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in non-decreasing order.
 */
public class _1428_LeftmostColumnWithAtLeastAOne {

  public static void main(String[] args) {
    System.out.println(
        "0 == " + leftMostColumnWithOne(new BinaryMatrix(new int[][] {{0, 0}, {1, 1}})));
    System.out.println(
        "1 == " + leftMostColumnWithOne(new BinaryMatrix(new int[][] {{0, 0}, {0, 1}})));
    System.out.println(
        "-1 == " + leftMostColumnWithOne(new BinaryMatrix(new int[][] {{0, 0}, {0, 0}})));
  }

  public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    List<Integer> dim = binaryMatrix.dimensions();
    int rows = dim.get(0), cols = dim.get(1);

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < rows; i++) {
      int indexOne = binarySearch(binaryMatrix, i);
      if (indexOne != -1) {
        ans = Math.min(ans, indexOne);
      }
    }

    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private static int binarySearch(BinaryMatrix bm, int row) {
    List<Integer> dim = bm.dimensions();
    int rows = dim.get(0), cols = dim.get(1);
    int left = 0, right = cols - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;

      if (bm.get(row, mid) == 0) {
        left = mid;
      } else {
        right = mid;
      }
    }
    if (bm.get(row, left) == 1) {
      return left;
    }
    if (bm.get(row, right) == 1) {
      return right;
    }

    return -1;
  }

  static class BinaryMatrix {
    int row, col;
    int[][] matrix;

    public BinaryMatrix(int[][] matrix) {
      this.matrix = matrix;
      this.row = matrix.length;
      this.col = matrix[0].length;
    }

    private int get(int row, int col) {
      return matrix[row][col];
    }

    private List<Integer> dimensions() {
      return Arrays.asList(row, col);
    }
  }
}
