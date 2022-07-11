package oa.az.q;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Power {
  public static void main(String[] args) {
    System.out.println(findTotalPower(Arrays.asList(2, 3, 2, 1)));
    System.out.println(findTotalPower(Arrays.asList(2, 1, 3)));
    System.out.println(findTotalPower(Arrays.asList(4, 1, 3)));
    System.out.println(findTotalPower(Arrays.asList(4, 1, 3)));
  }

  private static int findTotalPower(List<Integer> power) {
    int mod = 1000000007;
    long totalSum = 0;

    int n = power.size(), k = 1, cur = 0;
    int[][] mindp = new int[n][n];
    // construct an array for sum of subarray
    int[] sum = new int[n + 1];
    for (int num : power) {
      cur += num;
      sum[k++] = cur;
    }

    // create a matrix to find out the minimum integer between index i and j
    for (int i = 0; i < n; i++) {
      LinkedList<Integer> stack = new LinkedList<>();
      for (int j = i; j < n; j++) {
        while (!stack.isEmpty() && (power.get(stack.peekFirst()) > power.get(j))) {
          stack.removeFirst();
        }
        stack.addLast(j);
        mindp[i][j] = power.get(stack.peekFirst());
      }
    }
    // sum up all the possible subarrays using index i and j with the matrix and array
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        totalSum += mindp[i][j] * (sum[j + 1] - sum[i]);
      }
    }
    return  (int) (totalSum % mod);
  }
}
