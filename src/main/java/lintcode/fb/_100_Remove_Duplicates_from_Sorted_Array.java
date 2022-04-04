package lintcode.fb;

public class _100_Remove_Duplicates_from_Sorted_Array {

  public static int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int nonDuplicatedIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != nums[nonDuplicatedIndex]) {
        nums[++nonDuplicatedIndex] = nums[i];
      }
    }

    return nonDuplicatedIndex + 1;
  }

  public static void main(String[] args) {
    System.out.println(removeDuplicates(new int[0]));
    System.out.println(removeDuplicates(new int[] {1, 1, 2}));
    System.out.println(removeDuplicates(new int[] {-10, 0, 1, 2, 3}));
    System.out.println(removeDuplicates(new int[] {-15, -7, -6, -1, 1, 2, 6, 11, 15, 15}));
  }
}
