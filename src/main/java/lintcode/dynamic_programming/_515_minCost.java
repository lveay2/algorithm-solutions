package lintcode.dynamic_programming;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or
 * green. The cost of painting each house with a certain color is different. You have to paint all
 * the houses such that no two adjacent houses have the same color, and you need to cost the least.
 * Return the minimum cost.
 *
 * <p>The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost
 * of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 *
 * <p>样例 Example 1:
 *
 * <p>Input: [[14,2,11],[11,14,5],[14,3,10]] Output: 10 Explanation: Paint house 0 into blue, paint
 * house 1 into green, paint house 2 into blue. Minimum cost: 2 + 5 + 3 = 10.
 *
 * Example 2:
 *
 * <p>Input: [[1,2,3],[1,4,6]] Output: 3
 *
 * 注意事项 All costs are positive integers.
 */
public class _515_minCost {

    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int l = costs.length;
        int[][] dp = new int[l][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < l; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];
        }

        return Math.min(dp[l - 1][0], Math.min(dp[l - 1][1], dp[l - 1][2]));
    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}}));
    }

}
