package leetcode._201_300;

import java.util.Arrays;

public class _300_Longest_Increasing_Subsequence {

  public static int lengthOfLIS2(int[] nums) {
    int[] dp = new int[nums.length];
    int len = 0;
    for (int num : nums) {
      int i = Arrays.binarySearch(dp, 0, len, num);
      if (i < 0) {
        i = -(i + 1);
      }
      dp[i] = num;
      if (i == len) {
        len++;
      }
    }
    return len;
  }

  public static int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] f = new int[nums.length];
    Arrays.fill(f, 1);
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          f[i] = Math.max(f[i], f[j] + 1);
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for (int i : f) {
      max = Math.max(max, i);
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println("4 == " + lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println("4 == " + lengthOfLIS(new int[] {0, 1, 0, 3, 2, 3}));
    System.out.println("1 == " + lengthOfLIS(new int[] {7, 7, 7, 7, 7, 7, 7}));
    System.out.println("4 == " + lengthOfLIS2(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println("4 == " + lengthOfLIS2(new int[] {0, 1, 0, 3, 2, 3}));
    System.out.println("1 == " + lengthOfLIS2(new int[] {7, 7, 7, 7, 7, 7, 7}));
  }
}
