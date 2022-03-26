package leetcode._1_100;

import java.util.ArrayList;
import java.util.List;

/*
46. Permutations
Given an array nums of distinct integers, return all the possible
permutations. You can return the answer in any order.


Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]

Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class _46_Permutations {

    public static void main(String[] args) {
        System.out.println(
                "[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]] ==\n"
                + permute(new int[]{1, 2, 3}));
        System.out.println("[[0, 1], [1, 0]] == " + permute(new int[]{0, 1}));
        System.out.println("[[1]] == " + permute(new int[]{1}));
    }

    private static List<List<Integer>> result = null;

    public static List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(new ArrayList<>(), nums, used);
        return result;
    }

    private static void dfs(List<Integer> combo, int[] nums, boolean[] used) {
        if (combo.size() == nums.length) {
            result.add(new ArrayList<>(combo));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            combo.add(nums[i]);
            used[i] = true;
            dfs(combo, nums, used);
            combo.remove(combo.size() - 1);
            used[i] = false;
        }
    }

}
