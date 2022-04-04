package lintcode.twopointers;

public class _406_Minimum_Size_Subarray_Sum {

  public static int minimumSize(int[] nums, int s) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int min = Integer.MAX_VALUE;
    int start = 0, sum = 0;
    for (int end = 0; end < nums.length; end++) {
      sum += nums[end];

      while (sum >= s) {
        min = Math.min(min, end - start + 1);
        sum -= nums[start];
        start++;
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public static void main(String[] args) {
    System.out.println("2 == " + minimumSize(new int[] {2, 3, 1, 2, 4, 3}, 7));
    System.out.println("-1 == " + minimumSize(new int[] {1, 2, 3, 4, 5}, 100));
  }
}
