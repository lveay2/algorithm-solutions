package leetcode._1_100._40_combination_sum_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all
 * unique combinations in candidates where the candidate numbers sums to target.
 *
 * <p>Each number in candidates may only be used once in the combination.
 *
 * <p>Note:
 *
 * <p>All numbers (including target) will be positive integers. The solution set must not contain
 * duplicate combinations. Example 1:
 *
 * <p>Input: candidates = [10,1,2,7,6,1,5], target = 8, A solution set is: [ [1, 7], [1, 2, 5], [2,
 * 6], [1, 1, 6] ] Example 2:
 *
 * <p>Input: candidates = [2,5,2,1,2], target = 5, A solution set is: [ [1,2,2], [5] ]
 */
public class CombinationSumII {

  public static void main(String[] args) {
    System.out.println(new CombinationSumII().combinationSum(new int[] {2, 5, 2, 1, 2}, 5));

    System.out.println(new CombinationSumII().combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();

    if (candidates == null || candidates.length == 0) {
      return result;
    }

    Arrays.sort(candidates);

    List<Integer> combination = new ArrayList<>();

    dfs(candidates, 0, combination, target, result);

    return result;
  }

  private void dfs(
      int[] nums,
      int startIndex,
      List<Integer> combination,
      int target,
      List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>(combination));

      return;
    }

    for (int i = startIndex; i < nums.length; i++) {
      int n = nums[i];

      if (i != startIndex && nums[i] == nums[i - 1]) {
        continue;
      }

      if (nums[i] > target) {
        break;
      }

      combination.add(n);

      dfs(nums, i + 1, combination, target - n, result);

      combination.remove(combination.size() - 1);
    }
  }
}
