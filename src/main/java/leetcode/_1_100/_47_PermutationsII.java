package leetcode._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of numbers, nums, that might contain duplicates,
return all possible unique permutations in any order.

Example 1:
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

Example 2:
Input: nums = [1,2,3]
Output:
[[1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]]

Constraints:
1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
public class _47_PermutationsII {

    public static void main(String[] args) {
        System.out.println("[[1, 1, 2], [1, 2, 1], [2, 1, 1]] ==\n"
                + permuteUnique(new int[]{1, 1, 2}));
        System.out.println(
                "[[1, 1, 2], [1, 2, 1], [2, 1, 1], [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]] ==\n"
                        + permuteUnique(new int[]{1, 2, 3}));
    }

    static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
