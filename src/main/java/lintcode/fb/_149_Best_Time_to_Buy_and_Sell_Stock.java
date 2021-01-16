package lintcode.fb;

public class _149_Best_Time_to_Buy_and_Sell_Stock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE, maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            min = Math.min(min, price);
            maxProfit = Math.max(maxProfit, price - min);
        }

        return maxProfit == Integer.MIN_VALUE ? 0 : maxProfit;
    }

}
