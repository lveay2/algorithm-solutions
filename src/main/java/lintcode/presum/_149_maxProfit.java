package lintcode.presum;

/**
 * 149. 买卖股票的最佳时机
 * 中文English
 * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。
 *
 * 样例
 * 样例1
 *
 * 输入: [3, 2, 3, 1, 2]
 * 输出: 1
 * 说明：你可以在第三天买入，第四天卖出，利润是 2 - 1 = 1
 *
 * 样例2
 *
 * 输入: [1, 2, 3, 4, 5]
 * 输出: 4
 * 说明：你可以在第0天买入，第四天卖出，利润是 5 - 1 = 4
 *
 * 样例3
 *
 * 输入: [5, 4, 3, 2, 1]
 * 输出: 0
 * 说明：你可以不进行任何操作然后也得不到任何利润
 */
public class _149_maxProfit {

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;

        int profit = 0;
        for (int i : prices) {
            min = Math.min(min, i);
            profit = Math.max(profit, i - min);
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3, 2, 3, 1, 2}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maxProfit(new int[]{5, 4, 3, 2, 1}));
    }

}
