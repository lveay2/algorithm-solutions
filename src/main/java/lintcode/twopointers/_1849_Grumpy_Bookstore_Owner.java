package lintcode.twopointers;

/**
 * There is a bookstore. On the next nn days, customer[i]customer[i]customers will arrive on the
 * i-thi−th day and leave at the end of that day.
 *
 * <p>However, the bookstore owner's temper is sometimes good but sometimes bad. We use an array of
 * grumpygrumpy to indicate his temper is good or bad every day. If grumpy[i] = 1grumpy[i]=1, it
 * means that the owner's temper is very bad on the day of ii. If grumpy[i] = 0grumpy[i]=0, it means
 * that the owner has a good temper on the first day.
 *
 * <p>If the owner of the bookstore has a bad temper one day, it will cause all customers who come
 * on that day to give bad reviews to the bookstore. But if one day you have a good temper, then all
 * customers will give the bookstore favorable comments on that day.
 *
 * <p>The boss wanted to increase the number of people who gave favorable comments to the bookstore
 * as much as possible and came up with a way. He can keep a good temper for XX days in a row. But
 * this method can only be used once.
 *
 * <p>So how many people in this bookstore can give favorable comments to the bookstore when they
 * leave on this nn day?
 *
 * <p>样例 Input: [1,0,1,2,1,1,7,5] [0,1,0,1,0,1,0,1] 3 Output: 16 Explanation: The bookstore owner
 * keeps themselves not grumpy for the last 3 days. The maximum number of customers that can be
 * satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 */
public class _1849_Grumpy_Bookstore_Owner {

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        for (int i = 0; i < customers.length; i++) {
            if (i < X) {
                sum += customers[i];
            } else {
                sum += (1 - grumpy[i]) * customers[i];
            }
        }
        int result = sum;
        int left = 0, right = X;
        while (right < customers.length) {
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            if (grumpy[left] == 1) {
                sum -= customers[left];
            }

            result = Math.max(result, sum);
            right++;
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
    }

}
