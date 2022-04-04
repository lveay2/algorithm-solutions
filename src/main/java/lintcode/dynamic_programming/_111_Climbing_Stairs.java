package lintcode.dynamic_programming;

public class _111_Climbing_Stairs {

  public static void main(String[] args) {
    _111_Climbing_Stairs solution = new _111_Climbing_Stairs();
    System.out.println(solution.climbStairs(0));
    System.out.println(solution.climbStairs(1));
    System.out.println(solution.climbStairs(2));
    System.out.println(solution.climbStairs(10));
  }

  public int climbStairs(int n) {
    if (n == 0) {
      return n = 0;
    }

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
  }
}
