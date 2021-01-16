package leetcode._1_100._46_permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {

    public static void main(String[] args) {
        System.out.println(new Permutations().permute(new int[]{1, 2, 3}));

        System.out.println(new Permutations().permute2(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> permutation = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];

        dfs(nums, visited, permutation, result);

        return result;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));

            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);

            visited[i] = true;

            dfs(nums, visited, permutation, result);

            visited[i] = false;

            permutation.remove(permutation.size() - 1);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> combo = new ArrayList<>();

        dfs2(nums, combo, result);

        return result;
    }

    private void dfs2(int[] nums,
                      List<Integer> combo,
                      List<List<Integer>> result) {

        if (combo.size() == nums.length) {
            result.add(new ArrayList<>(combo));

            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (combo.contains(nums[i])) {
                continue;
            }

            combo.add(nums[i]);

            dfs2(nums, combo, result);

            combo.remove(combo.size() - 1);
        }

    }

}
