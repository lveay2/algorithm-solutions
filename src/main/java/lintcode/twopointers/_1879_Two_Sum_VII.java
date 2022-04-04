package lintcode.twopointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1879_Two_Sum_VII {

  public static List<List<Integer>> twoSumVII(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return Collections.emptyList();
    }

    List<List<Integer>> result = new ArrayList<>();
    int left = 0, right = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > nums[right]) {
        right = i;
      }
      if (nums[i] < nums[left]) {
        left = i;
      }
    }

    while (nums[left] < nums[right]) {
      if (nums[left] + nums[right] < target) {
        left = findNextLeft(nums, left);
        if (left == -1) {
          break;
        }
      } else if (nums[left] + nums[right] > target) {
        right = findNextRight(nums, right);
        if (right == -1) {
          break;
        }
      } else {
        List<Integer> temp = new ArrayList<>();
        if (left < right) {
          temp.add(left);
          temp.add(right);
        } else {
          temp.add(right);
          temp.add(left);
        }
        result.add(temp);
        left = findNextLeft(nums, left);
        if (left == -1) {
          break;
        }
      }
    }

    return result;
  }

  private static int findNextLeft(int[] nums, int left) {
    if (nums[left] >= 0) {
      // find next non-negative left from left + 1 to n - 1
      for (int i = left + 1; i < nums.length; i++) {
        if (nums[i] >= 0) {
          return i;
        }
      }
    } else {
      // find a bigger negative left from left - 1 to 0
      for (int i = left - 1; i >= 0; i--) {
        if (nums[i] < 0) {
          return i;
        }
      }
      // find a positive left from 0 to n - 1
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] >= 0) {
          return i;
        }
      }
    }

    return -1;
  }

  private static int findNextRight(int[] nums, int right) {
    if (nums[right] > 0) {
      // find next bigger positive right from n - 1 to 0
      for (int i = right - 1; i >= 0; i--) {
        if (nums[i] > 0) {
          return i;
        }
      }
      // find a smaller negative right from 0 to n -1
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= 0) {
          return i;
        }
      }
    } else {
      // find a samller negative right from right + 1 to n - 1
      for (int i = right + 1; i < nums.length; i++) {
        if (nums[i] < 0) {
          return i;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(twoSumVII(new int[] {0, -1, 2, -3, 4}, 1));
    System.out.println(twoSumVII(new int[] {11, -14, -23, -24, 27, -36, 48, 66, -70, -72}, -60));
  }
}
