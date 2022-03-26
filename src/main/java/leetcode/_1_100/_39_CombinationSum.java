package leetcode._1_100;

import java.util.ArrayList;
import java.util.List;

/*
39. Combination Sum
Given an array of distinct integers candidates and a target
integer target, return a list of all unique combinations of
candidates where the chosen numbers sum to target. You may
return the combinations in any order.

The same number may be chosen from candidates an unlimited
number of times. Two combinations are unique if the frequency
of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that
sum up to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []

Constraints:
1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
 */
public class _39_CombinationSum {

    public static void main(String[] args) {
        System.out.println("[[2, 2, 3], [7]] ==\n" + combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println("[[2, 2, 2, 2], [2, 3, 3], [3, 5]] ==\n" + combinationSum(new int[]{2,3,5}, 8));
        System.out.println("[] == " + combinationSum(new int[]{2}, 1));
    }

    static List<List<Integer>> result;
    static List<Integer> combo;
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        combo = new ArrayList<>();
        dfs(combo, candidates, 0, target);
        return result;
    }

    private static void dfs(List<Integer> combo, int[] nums, int start, int target) {
        int sum = 0;
        for (int n: combo) {
            sum += n;
        }
        if (sum == target) {
            result.add(new ArrayList<>(combo));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            combo.add(nums[i]);
            dfs(combo, nums, i, target);
            combo.remove(combo.size() - 1);
        }
    }

}
