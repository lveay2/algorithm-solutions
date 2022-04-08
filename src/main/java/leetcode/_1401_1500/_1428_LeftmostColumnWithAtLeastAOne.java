package leetcode._1401_1500;

import java.util.List;

/*
1428. Leftmost Column with at Least a One

 */
public class _1428_LeftmostColumnWithAtLeastAOne {

  public static void main(String[] args) {
    
  }
  public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
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

  private int binarySearch(BinaryMatrix bm, int row){
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


}
