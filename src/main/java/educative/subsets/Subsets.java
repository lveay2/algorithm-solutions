package educative.subsets;

import java.util.*;

public class Subsets {

  public static List<List<Integer>> findSubsets(int[] nums) {
    List<List<Integer>> subsets = new ArrayList<>();
    // start by adding the empty subset
    subsets.add(new ArrayList<>());
    for (int currentNumber : nums) {
      // we will take all existing subsets and insert the current number in them to create new
      // subsets
      int n = subsets.size();
      for (int i = 0; i < n; i++) {
        // create a new subset from the existing subset and insert the current element to it
        List<Integer> set = new ArrayList<>(subsets.get(i));
        set.add(currentNumber);
        subsets.add(set);
      }
    }
    return subsets;
  }

  public static void main(String[] args) {
    List<List<Integer>> result = Subsets.findSubsets(new int[] {1, 3});
    System.out.println("Here is the list of subsets: [[], [1], [3], [1, 3]] == " + result);

    result = Subsets.findSubsets(new int[] {1, 5, 3});
    System.out.println(
        "Here is the list of subsets: [[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]] == "
            + result);

    result = Subsets.findSubsetsDFS(new int[] {1, 3});
    System.out.println("Here is the list of subsets: [[], [1], [3], [1, 3]] == " + result);

    result = Subsets.findSubsetsDFS(new int[] {1, 5, 3});
    System.out.println(
        "Here is the list of subsets: [[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]] == "
            + result);
  }

  private static List<List<Integer>> findSubsetsDFS(int[] nums) {
    List<List<Integer>> subsets = new ArrayList<>();

    Queue<List<Integer>> q = new LinkedList<>();
    q.offer(new LinkedList<>());

    while (!q.isEmpty()) {
      List<Integer> current = q.poll();
      //            System.out.println("current: " + current);
      subsets.add(current);
      for (int i = 0; i < nums.length; i++) {
        Set<Integer> currentSet = new HashSet<>(current);
        if (current.isEmpty() || !currentSet.contains(nums[i])) {
          List<Integer> newCombo = new LinkedList<>(current);
          newCombo.add(nums[i]);
          q.offer(newCombo);
        }
      }
    }

    return subsets;
  }
}
