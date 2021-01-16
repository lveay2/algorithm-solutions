package leetcode._1_100._39_combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));

        System.out.println(new CombinationSum().combinationSum(new int[]{2, 2, 3, 6, 7}, 7));

        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 5}, 8));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return result;
        }

        int[] nums = sortAndDedup(candidates);

        List<Integer> combination = new ArrayList<>();

        dfs(nums, 0, combination, target, result);

        return result;
    }

    private int[] sortAndDedup(int[] candidates) {
        Arrays.sort(candidates);

        int index = 0;

        for (int i = 1; i < candidates.length; i++) {
            if (candidates[index] != candidates[i]) {
//                index++;
//
//                candidates[index] = candidates[i];
                // OR
                candidates[++index] = candidates[i];
            }
        }

        int[] nums = new int[index + 1];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = candidates[i];
        }

        return nums;
    }

    private void dfs(int[] nums,
                     int startIndex,
                     List<Integer> combination,
                     int target,
                     List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));

            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            int num = nums[i];

            if (target - num < 0) {
                break;
            }

            combination.add(num);

            dfs(nums, i, combination, target - num, result);

            combination.remove(combination.size() - 1);
        }

    }

}
