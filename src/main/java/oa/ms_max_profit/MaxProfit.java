package oa.ms_max_profit;

public class MaxProfit {


    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxPrice(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));

//        System.out.println(new MaxProfit().maxPriceWithOneTransaction(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    public int maxPrice(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    public int maxPriceWithOneTransaction(int[] prices) {

        return 0;
    }

}
