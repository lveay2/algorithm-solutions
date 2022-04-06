package leetcode._301_400;

import java.util.Arrays;

/*
370. Range Addition
You are given an integer length and an array updates where
updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and
you have some operation to apply on arr. In the ith operation,
you should increment all the elements
arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.

Example 1:
Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]

Example 2:
Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]

Constraints:
1 <= length <= 105
0 <= updates.length <= 104
0 <= startIdxi <= endIdxi < length
-1000 <= inci <= 1000
 */
public class _370_RangeAddition {

  public static void main(String[] args) {
    System.out.println(
        "[-2, 0, 3, 5, 3] ==\n"
            + Arrays.toString(getModifiedArray(5, new int[][] {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}})));
    System.out.println(
        "[0, -4, 2, 2, 2, 4, 4, -4, -4, -4] ==\n"
            + Arrays.toString(
                getModifiedArray(10, new int[][] {{2, 4, 6}, {5, 6, 8}, {1, 9, -4}})));
  }

  public static int[] getModifiedArray(int length, int[][] updates) {
    int[] nums = new int[length];
    Diff diff = new Diff(nums);

    for (int[] update : updates) {
      int left = update[0];
      int right = update[1];
      int value = update[2];
      diff.update(left, right, value);
    }

    return diff.result();
  }

  static class Diff {
    int[] diffs = null;

    public Diff(int[] nums) {
      int n = nums.length;
      diffs = new int[n];
      diffs[0] = 0;
      for (int i = 1; i < n; i++) {
        diffs[i] = nums[i] - diffs[i - 1];
      }
    }

    public void update(int left, int right, int value) {
      diffs[left] += value;
      if (right + 1 < diffs.length) {
        diffs[right + 1] -= value;
      }
    }

    public int[] result() {
      int[] res = new int[diffs.length];

      res[0] = diffs[0];
      for (int i = 1; i < diffs.length; i++) {
        res[i] = res[i - 1] + diffs[i];
      }

      return res;
    }
  }
}
