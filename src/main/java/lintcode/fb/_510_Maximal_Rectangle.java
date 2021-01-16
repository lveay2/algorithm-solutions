package lintcode.fb;

import java.util.Stack;

public class _510_Maximal_Rectangle {

    public static int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {//每个位置上方有多少连续的1
            for (int j = 0; j < m; j++) {
                if (i == 0 && matrix[i][j]) {
                    dp[i][j] = 1;
                    continue;
                }
                if (matrix[i][j]) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) { //把每一行作为底找最大矩形
            ans = Math.max(ans, largestRectangleArea(dp[i]));
        }
        return ans;
    }

    private static int largestRectangleArea(int[] height) {
        Stack<Integer> S = new Stack<>();
        height[height.length - 1] = 0;
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (S.empty() || height[i] > height[S.peek()]) {
                S.push(i);
            } else {
                int tmp = S.pop();
                sum = Math.max(sum, height[tmp] * (S.empty() ? i : i - S.peek() - 1));
                i--;  //拿着右边界， 寻找左边界；
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(maximalRectangle(new boolean[][]{
                {true, true, false, false, true},
                {false, true, false, false, true},
                {false, false, true, true, true},
                {false, false, true, true, true},
                {false, false, false, false, true}
        }));
    }

}
