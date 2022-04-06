package leetcode._301_400;

import java.util.Arrays;

/*
303. Range Sum Query - Immutable
Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums
between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

Example 1:
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

Constraints:
1 <= nums.length <= 104
-105 <= nums[i] <= 105
0 <= left <= right < nums.length
At most 104 calls will be made to sumRange.
 */
public class _303_RangeSumQueryImmutable {

  public static void main(String[] args) {
    NumArray na = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
    System.out.println(na);
    System.out.println("1 == " + na.sumRange(0, 2));
    System.out.println("-1 == " + na.sumRange(2, 5));
    System.out.println("-3 == " + na.sumRange(0, 5));

    NumArray2 na2 = new NumArray2(new int[] {-2, 0, 3, -5, 2, -1});
    System.out.println(na2);
    System.out.println("1 == " + na2.sumRange(0, 2));
    System.out.println("-1 == " + na2.sumRange(2, 5));
    System.out.println("-3 == " + na2.sumRange(0, 5));
    System.out.println("1 == " + na2.sumRange(4, 5));

  }

  static class NumArray2 {

    int[] presum = null;
    int[] numArray = null;

    public NumArray2(int[] nums) {
      numArray = nums;
      int n = nums.length;
      presum = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        presum[i] = presum[i - 1] + nums[i - 1];
      }
    }

    // nums     -2  0 3 -5  2 -1
    // presum 0 -2 -2 1 -4 -2 -3
    public int sumRange(int left, int right) {
      return presum[right + 1] - presum[left];
    }

    @Override
    public String toString() {
      return "NumArray{"
          + "presum="
          + Arrays.toString(presum)
          + ", numArray="
          + Arrays.toString(numArray)
          + '}';
    }
  }

  static class NumArray {

    int[] presum = null;
    int[] numArray = null;

    public NumArray(int[] nums) {
      numArray = nums;
      int n = nums.length;
      presum = new int[n];
      presum[0] = nums[0];
      for (int i = 1; i < n; i++) {
        presum[i] = presum[i - 1] + nums[i];
      }
    }

    public int sumRange(int left, int right) {
      if (left == 0) {
        return presum[right];
      }

      return presum[right] - presum[left - 1];
    }

    @Override
    public String toString() {
      return "NumArray{"
          + "presum="
          + Arrays.toString(presum)
          + ", numArray="
          + Arrays.toString(numArray)
          + '}';
    }
  }
}
