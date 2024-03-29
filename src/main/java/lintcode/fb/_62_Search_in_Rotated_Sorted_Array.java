package lintcode.fb;

public class _62_Search_in_Rotated_Sorted_Array {

  public static int search(int[] A, int target) {
    if (A == null || A.length == 0) {
      return -1;
    }

    int left = 0;
    int right = A.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;

      if (A[mid] == target) {
        return mid;
      }

      if (A[left] < A[mid]) {
        if (A[left] <= target && target <= A[mid]) {
          right = mid;
        } else {
          left = mid;
        }
      } else {
        if (A[mid] <= target && target <= A[right]) {
          left = mid;
        } else {
          right = mid;
        }
      }
    }

    if (A[left] == target) return left;
    if (A[right] == target) return right;

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search(new int[] {4, 5, 1, 2, 3}, 1));
    System.out.println(search(new int[] {4, 5, 1, 2, 3}, 0));
    System.out.println(search(new int[] {5}, 5));
  }
}
