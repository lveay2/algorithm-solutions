package leetcode._1_100._47_permutations_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class PermutationsII {

    public static void main(String[] args) {
//        System.out.println("[[1, 1, 2], [1, 2, 1], [2, 1, 1]] == " + new PermutationsII().permuteUnique(new int[]{1,1,2}));
//
//        System.out.println("[[1, 2, 2], [2, 1, 2], [2, 2, 1]] == " + new PermutationsII().permuteUnique(new int[]{1, 2, 2}));

        System.out.println("[[0, 3, 3, 3], [3, 0, 3, 3], [3, 3, 0, 3], [3, 3, 3, 0]] == " + new PermutationsII().permuteUnique(new int[]{3, 3, 0, 3}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        // Sort first
        Arrays.sort(nums);

        List<Integer> permutation = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];

        dfs(nums, visited, permutation, result);

        return result;
    }

    private void dfs(int[] nums,
                     boolean[] visited,
                     List<Integer> permutation,
                     List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));

            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            permutation.add(nums[i]);

            visited[i] = true;

            dfs(nums, visited, permutation, result);

            permutation.remove(permutation.size() - 1);

            visited[i] = false;
        }

    }

}
