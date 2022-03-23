package leetcode._1_100;

import java.util.ArrayList;
import java.util.List;

/*
Given two integers n and k, return all possible combinations of
k numbers out of the range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Example 2:
Input: n = 1, k = 1
Output: [[1]]
 */
public class _77_Combinations {

    public static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        dfs(new ArrayList<>(), 1, n, k);
        return result;
    }

    private static void dfs(List<Integer> combo, int first, int n, int k) {
        if (combo.size() == k) {
            result.add(new ArrayList<>(combo));
            return;
        }

        for (int i = first; i <= n; i++) {
            combo.add(i);
            dfs(combo, i + 1, n, k);
            combo.remove(combo.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("[[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]" +
                " equals\n" + combine(4, 2));
        System.out.println("[[1]] equals\n" + combine(1, 1));
    }
}
