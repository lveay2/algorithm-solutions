package educative.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Given a set of numbers that might contain duplicates, find all of its distinct subsets. */
public class SubsetWithDuplicates {

  public static List<List<Integer>> findSubsets(int[] nums) {
    // sort the numbers to handle duplicates
    Arrays.sort(nums);
    List<List<Integer>> subsets = new ArrayList<>();
    subsets.add(new ArrayList<>());
    int startIndex = 0, endIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      startIndex = 0;
      // if current and the previous elements are same, create new subsets only from the subsets
      // added in the previous step
      if (i > 0 && nums[i] == nums[i - 1]) {
        startIndex = endIndex;
      }
      endIndex = subsets.size();
      System.out.println("startIndex: " + startIndex + " endIndex: " + endIndex);
      for (int j = startIndex; j < endIndex; j++) {
        // create a new subset from the existing subset and add the current element to it
        List<Integer> set = new ArrayList<>(subsets.get(j));
        set.add(nums[i]);
        subsets.add(set);
      }
    }
    return subsets;
  }

  public static void main(String[] args) {
    List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] {1, 3, 3});
    System.out.println(
        "Here is the list of subsets: \n"
            + "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3]] == \n"
            + result);

    result = SubsetWithDuplicates.findSubsets(new int[] {1, 5, 3, 3});
    System.out.println(
        "Here is the list of subsets: \n"
            + "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]] == \n"
            + result);
  }
}
