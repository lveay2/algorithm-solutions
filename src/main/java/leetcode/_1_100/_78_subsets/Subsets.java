package leetcode._1_100._78_subsets;

import java.util.*;

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
 *
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1, 2, 3}));
        System.out.println(new Subsets().subsets2(new int[]{1, 5, 3}));
    }

    // BFS LinkedList
    public List<List<Integer>> subsets(int[] nums) {
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
            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> nextSubset = new LinkedList<>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }

        return r;
    }

    // BFS TreeSet
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return r;
        }

        Arrays.sort(nums);
        Queue<TreeSet<Integer>> queue = new LinkedList<>();
        queue.offer(new TreeSet<>());
        while (!queue.isEmpty()) {
            TreeSet<Integer> subset = queue.poll();
            r.add(new LinkedList<>(subset));
            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.last() < nums[i]) {
                    TreeSet<Integer> nextSubset = new TreeSet<>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }

        return r;
    }

}
