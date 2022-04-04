package leetcode._1_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
78. Subsets
Given an integer array nums of unique elements,
return all possible subsets (the power set).

The solution set must not contain duplicate subsets.
Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class _78_Subsets {

  private static List<List<Integer>> result = null;

  public static void main(String[] args) {
    System.out.println(
        "[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]] == \n"
            + subsets(new int[] {1, 2, 3}));
    System.out.println("[[], [0]] == " + subsets(new int[] {0}));

    System.out.println(
        "[[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]] == \n"
            + subsetsBFS(new int[] {1, 2, 3}));
    System.out.println("[[], [0]] == " + subsetsBFS(new int[] {0}));
  }

  public static List<List<Integer>> subsets(int[] nums) {
    result = new ArrayList<>();
    dfs(new ArrayList<>(), nums, 0);
    return result;
  }

  private static void dfs(List<Integer> combo, int[] nums, int index) {
    result.add(new ArrayList<>(combo));

    for (int i = index; i < nums.length; i++) {
      combo.add(nums[i]);
      dfs(combo, nums, i + 1);
      combo.remove(combo.size() - 1);
    }
  }

  private static List<List<Integer>> subsetsBFS(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();

    if (nums == null || nums.length == 0) {
      return r;
    }

    //        Arrays.sort(nums);
    Queue<List<Integer>> queue = new LinkedList<>();
    queue.offer(new LinkedList<>());
    while (!queue.isEmpty()) {
      List<Integer> subset = queue.poll();
      r.add(subset);
      for (int num : nums) {
        if (subset.size() == 0 || subset.get(subset.size() - 1) < num) {
          List<Integer> nextSubset = new LinkedList<>(subset);
          nextSubset.add(num);
          queue.offer(nextSubset);
        }
      }
    }

    return r;
  }
}
