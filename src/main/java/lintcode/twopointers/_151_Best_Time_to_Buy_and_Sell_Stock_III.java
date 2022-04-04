package lintcode.twopointers;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * <p>Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * <p>样例 Example 1
 *
 * <p>Input : [4,4,6,1,1,4,2,5] Output : 6
 *
 * <p>注意事项 You may not engage in multiple transactions at the same time (ie, you must sell the stock
 * before you buy again).
 */
public class _151_Best_Time_to_Buy_and_Sell_Stock_III {

  public static int maxProfit(int[] prices) {
    int n = prices.length;
    int totalCost = 0;

    for (int i = 0; i < n; i++) {
      int leftCost = getCost(prices, 0, i);
      int rightCost = getCost(prices, i, n);

      totalCost = Math.max(totalCost, leftCost + rightCost);
    }

    return totalCost;
  }

  private static int getCost(int[] prices, int i, int j) {
    int min = Integer.MAX_VALUE;
    int cost = 0;
    while (i < j) {
      min = Math.min(min, prices[i]);
      cost = Math.max(cost, prices[i] - min);
      i++;
    }

    return cost;
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[] {4, 4, 6, 1, 1, 4, 2, 5}));
  }
}
