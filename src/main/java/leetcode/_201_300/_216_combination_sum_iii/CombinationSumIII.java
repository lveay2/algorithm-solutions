package leetcode._201_300._216_combination_sum_iii;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        System.out.println("[[1, 2, 4]] == " + new CombinationSumIII().combinationSum3(3, 7));

        System.out.println("[[1, 2, 6], [1, 3, 5], [2, 3, 4]] == " + new CombinationSumIII().combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (k == 0) {
            return result;
        }

        List<Integer> combo = new ArrayList<>();

        dfs(1, combo, k, n, result);

        return result;
    }

    private void dfs(int index, List<Integer> combo, int k, int target, List<List<Integer>> result) {
        if (target == 0 && combo.size() == k) {
            result.add(new ArrayList<>(combo));

            return;
        }

        for (int i = index; i < 10; i++) {
            if (i > target) {
                break;
            }

            combo.add(i);

            dfs(i + 1, combo, k, target - i, result);

            combo.remove(combo.size() - 1);
        }

    }

}
