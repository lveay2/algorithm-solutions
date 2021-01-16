package leetcode._1_100._78_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class SubsetsDFS {

    public static void main(String[] args) {
        System.out.println(new SubsetsDFS().subsets(new int[]{1, 2, 3}));
    }

    // DFS
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        List<Integer> combo = new ArrayList<>();

        dfs(nums, 0, combo, result);

        return result;
    }

    private void dfs(int[] nums,
                     int index,
                     List<Integer> combo,
                     List<List<Integer>> result) {

        result.add(new ArrayList<>(combo));

        for (int i = index; i < nums.length; i++) {
            combo.add(nums[i]);

            dfs(nums, i + 1, combo, result);

            combo.remove(combo.size() - 1);
        }
    }

}
