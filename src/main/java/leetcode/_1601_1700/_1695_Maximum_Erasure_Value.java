package leetcode._1601_1700;

import java.util.HashMap;
import java.util.Map;

public class _1695_Maximum_Erasure_Value {

  public static int maximumUniqueSubarray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    Map<Integer, Integer> map = new HashMap<>();

    int max = Integer.MIN_VALUE;
    int sum = 0;
    int start = 0;
    for (int end = 0; end < nums.length; end++) {
      int en = nums[end];
      map.put(en, map.getOrDefault(en, 0) + 1);
      sum += en;

      while (map.get(en) > 1) {
        int sn = nums[start];
        map.put(sn, map.get(sn) - 1);
        sum -= sn;
        start++;
      }

      max = Math.max(max, sum);
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(maximumUniqueSubarray(new int[] {4, 2, 4, 5, 6}));
    System.out.println(maximumUniqueSubarray(new int[] {5, 2, 1, 2, 5, 2, 1, 2, 5}));
  }
}
