package leetcode._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
90. Subsets II
Given an integer array nums that may contain duplicates,
return all possible subsets (the power set).

The solution set must not contain duplicate subsets.
Return the solution in any order.


Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class _90_SubsetsII {

  private static List<List<Integer>> result = null;

  public static void main(String[] args) {
    System.out.println(
        "[[], [1], [1, 2], [1, 2, 2], [2], [2, 2]] ==\n" + subsetsWithDup(new int[] {1, 2, 2}));
    System.out.println("[[],[0]] ==" + subsetsWithDup(new int[] {0}));
  }

  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    result = new ArrayList<>();
    Arrays.sort(nums);
    dfs(new ArrayList<>(), nums, 0);
    return result;
  }

  private static void dfs(List<Integer> combo, int[] nums, int start) {
    result.add(new ArrayList<>(combo));

    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }

      combo.add(nums[i]);
      dfs(combo, nums, i + 1);
      combo.remove(combo.size() - 1);
    }
  }
}
