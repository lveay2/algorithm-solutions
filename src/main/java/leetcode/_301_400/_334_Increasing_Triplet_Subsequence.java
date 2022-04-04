package leetcode._301_400;

public class _334_Increasing_Triplet_Subsequence {

  public static void main(String[] args) {
    _334_Increasing_Triplet_Subsequence solution = new _334_Increasing_Triplet_Subsequence();
    System.out.println(solution.increasingTriplet(new int[] {1, 2, 3, 4, 5}));
    System.out.println(solution.increasingTriplet(new int[] {5, 4, 3, 2, 1}));
    System.out.println(solution.increasingTriplet(new int[] {2, 1, 5, 0, 4, 6}));
    System.out.println(solution.increasingTriplet(new int[] {1, 2, 1, 0, 5}));
    System.out.println(solution.increasingTriplet(new int[] {1, 2, 1, 3, 5}));
    System.out.println(solution.increasingTriplet(new int[] {20, 100, 10, 12, 5, 13}));
  }

  public boolean increasingTriplet(int[] nums) {
    if (nums == null || nums.length < 3) {
      return false;
    }

    int count = 0;
    int start = 0;
    for (int end = 1; end < nums.length; end++) {
      if (nums[end - 1] < nums[end]) {
        count++;
      }

      while (start < end && end < nums.length && nums[end - 1] >= nums[end]) {
        start++;
        if (count > 0) {
          count--;
        }
      }

      if (count == 2) {
        return true;
      }
    }

    return count == 2;
  }
}
