package oa.az.shopping_options;

import java.util.Arrays;

/**
 * A customer wants to buy a pair of jeans, a pair of shoes, a skirt, and a top but has a limited budget in dollars.
 * Given different pricing options for each product, determine how many options our customer has to buy 1 of each
 * product. You cannot spend more money than the budgeted amount.
 * <p>
 * Example
 * priceOfJeans = new int[]{2, 3]
 * priceOfShoes = new int[]{4]
 * priceOfSkirts = new int[]{2, 3]
 * priceOfTops = new int[]{1, 2]
 * budgeted = 10
 * <p>
 * The customer must buy shoes for 4 dollars since there is only one option. This leaves 6 dollars to spend on the
 * other 3 items. Combinations of prices paid for jeans, skirts, and tops respectively that add up to 6 dollars or
 * less are new int[]{2, 2, 2}, new int[]{2, 2, 1}, new int[]{3, 2, 1}, new int[]{2, 3, 1]. There are 4 ways the
 * customer can purchase all 4 items.
 * <p>
 * Function Description
 * <p>
 * Complete the getNumberOfOptions function in the editor below. The function must return an integer which represents
 * the number of options present to buy the four items.
 * <p>
 * getNumberOfOptions has 5 parameters:
 * int[] priceOfJeans: An integer array, which contains the prices of the pairs of jeans available.
 * int[] priceOfShoes: An integer array, which contains the prices of the pairs of shoes available.
 * int[] priceOfSkirts: An integer array, which contains the prices of the skirts available.
 * int[] priceOfTops: An integer array, which contains the prices of the tops available.
 * int dollars: the total number of dollars available to shop with.
 * <p>
 * Constraints
 * <p>
 * 1 ≤ a, b, c, d ≤ 103
 * 1 ≤ dollars ≤ 109
 * 1 ≤ price of each item ≤ 109
 * Note: a, b, c and d are the sizes of the four price arrays
 */
public class ShoppingOptions {

    private static int getOptions(int[] a, int[] b, int[] c, int[] d, int dollars) {
        int longest = Math.max(a.length, Math.max(b.length, Math.max(c.length, d.length)));

        if (d.length == longest) {
            return helper(d, a, b, c, dollars);
        }

        if (c.length == longest) {
            return helper(c, a, b, d, dollars);
        }

        if (b.length == longest) {
            return helper(b, a, c, d, dollars);
        }

        return helper(a, b, c, d, dollars);
    }

    private static int helper(int[] longest, int[] a, int[] b, int[] c, int dollars) {
        Arrays.sort(longest);
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        int[] abSum = new int[a.length * b.length];
        int index = 0;
        for (int ai : a) {
            if (ai >= dollars) {
                break;
            }

            for (int bi : b) {
                if (bi >= dollars) {
                    break;
                }

                if (ai + bi < dollars) {
                    abSum[index++] = ai + bi;
                } else {
                    break;
                }
            }
        }


        int abcIndex = 0;
        int[] abcSum = new int[index * c.length];
        for (int i = 0; i < index; i++) {
            if (abSum[i] == 0) {
                break;
            }

            for (int ci : c) {
                if (ci >= dollars) {
                    break;
                }

                if (abSum[i] + ci < dollars) {
                    abcSum[abcIndex++] = abSum[i] + ci;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < abcIndex; i++) {
            if (abcSum[i] == 0) {
                break;
            }

            for (int di : longest) {
                if (abcSum[i] + di <= dollars) {
                    result++;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getOptions(new int[]{2, 3}, new int[]{4}, new int[]{2, 3}, new int[]{1, 2}, 10) == 4);
        System.out.println(getOptions(new int[]{2, 3}, new int[]{4}, new int[]{2, 3}, new int[]{1, 2}, 9) == 1);
        System.out.println(getOptions(new int[]{6}, new int[]{1, 1, 1, 1}, new int[]{4, 5, 6}, new int[]{1}, 12) == 4);
        System.out.println(getOptions(new int[]{6}, new int[]{1, 1, 1, 1}, new int[]{4, 5, 6}, new int[]{1}, 13) == 8);
        System.out.println(getOptions(new int[]{6}, new int[]{1, 1, 1, 1}, new int[]{4, 5, 6}, new int[]{1}, 14) == 12);
        System.out.println(getOptions(new int[]{100}, new int[]{1, 1, 1, 1}, new int[]{4, 5, 6}, new int[]{1}, 99) == 0);
        System.out.println(getOptions(new int[]{1}, new int[]{1}, new int[]{1}, new int[]{1}, 4) == 1);
        System.out.println(getOptions(new int[]{1}, new int[]{1}, new int[]{1}, new int[]{1}, 3) == 0);
    }

}
