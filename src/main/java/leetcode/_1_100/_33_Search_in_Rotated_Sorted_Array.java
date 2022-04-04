package leetcode._1_100;

public class _33_Search_in_Rotated_Sorted_Array {

  public static int search(int[] nums, int target) {
    if (nums.length == 1) {
      return target == nums[0] ? 0 : -1;
    }

    int right = nums.length - 1;
    int left = 0;

    while (left + 1 < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[left] < nums[mid]) {
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[mid] < target && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }

    if (nums[left] == target) {
      return left;
    }

    if (nums[right] == target) {
      return right;
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
    System.out.println(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
    System.out.println(search(new int[] {1}, 0));
    System.out.println(search(new int[] {1, 3, 5}, 1));
    System.out.println(search(new int[] {1, 3, 5}, 5));
    System.out.println(search(new int[] {5, 1, 3}, 5));
    System.out.println(search(new int[] {5, 1, 3}, 3));
    System.out.println(search(new int[] {6, 7, 0, 1, 2, 3, 4}, 7));
  }
}
