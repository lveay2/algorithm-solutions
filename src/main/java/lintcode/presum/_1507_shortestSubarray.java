package lintcode.presum;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1507. Shortest Subarray with Sum at Least K 中文English Return the length of the shortest,
 * non-empty, contiguous subarray of A with sum at least K.
 *
 * <p>If there is no non-empty subarray with sum at least K, return -1.
 *
 * <p>样例 Example 1:
 *
 * <p>Input: A = [1], K = 1 Output: 1
 *
 * <p>Example 2:
 *
 * <p>Input: A = [1,2], K = 4 Output: -1
 */
public class _1507_shortestSubarray {

  public static int shortestSubarray(int[] A, int K) {
    int l = A.length;
    int min = Integer.MAX_VALUE;
    int[] presums = new int[l + 1];
    for (int i = 0; i < l; i++) {
      presums[i + 1] = A[i] + presums[i];
    }

    Deque<Integer> d = new ArrayDeque<>();
    for (int i = 0; i < l + 1; i++) {
      while (d.size() > 0 && presums[i] - presums[d.getFirst()] >= K) {
        min = Math.min(min, i - d.pollFirst());
      }

      while (d.size() > 0 && presums[i] <= presums[d.getLast()]) {
        d.pollLast();
      }

      d.addLast(i);
    }

    return min <= l ? min : -1;
  }

  public static void main(String[] args) {
    System.out.println(shortestSubarray(new int[] {84, -37, 32, 40, 95}, 167));
    //    System.out.println(shortestSubarray(new int[]{1}, 1));
    //    System.out.println(shortestSubarray(new int[]{1,2}, 4));
  }
}
