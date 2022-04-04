package leetcode._201_300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _229_Majority_Element_II {

  public static List<Integer> majorityElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Collections.emptyList();
    }

    int count1 = 0, count2 = 0;
    Integer num1 = null, num2 = null;

    for (int n : nums) {
      if (num1 != null && n == num1) {
        count1++;
      } else if (num2 != null && n == num2) {
        count2++;
      } else if (count1 == 0) {
        num1 = n;
        count1++;
      } else if (count2 == 0) {
        num2 = n;
        count2++;
      } else {
        count1--;
        count2--;
      }
    }

    count1 = 0;
    count2 = 0;
    for (int n : nums) {
      if (num1 != null && n == num1) {
        count1++;
      }
      if (num2 != null && n == num2) {
        count2++;
      }
    }

    List<Integer> result = new ArrayList<>();

    int n = nums.length;
    if (count1 > n / 3) {
      result.add(num1);
    }
    if (count2 > n / 3) {
      result.add(num2);
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(majorityElement(new int[] {3, 2, 3}));
  }
}
