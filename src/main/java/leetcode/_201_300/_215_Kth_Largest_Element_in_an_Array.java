package leetcode._201_300;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class _215_Kth_Largest_Element_in_an_Array {

  public static int findKthLargest(int[] nums, int k) {
    Queue<Integer> queue = new PriorityQueue<>();

    for (int num : nums) {
      queue.offer(num);

      if (queue.size() > k) {
        queue.poll();
      }
    }

    return queue.peek();
  }

  public static int findKthLargest2(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  public static void main(String[] args) {
    System.out.println(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
    System.out.println(findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    System.out.println(findKthLargest2(new int[] {3, 2, 1, 5, 6, 4}, 2));
    System.out.println(findKthLargest2(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }
}
