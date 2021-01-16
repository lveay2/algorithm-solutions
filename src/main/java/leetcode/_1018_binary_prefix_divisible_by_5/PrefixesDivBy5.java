package leetcode._1018_binary_prefix_divisible_by_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)
 * <p>
 * Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.
 * <p>
 * Example 1:
 * <p>
 * Input: [0,1,1]
 * Output: [true, false, false]
 * Explanation:
 * The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.
 * Example 2:
 * <p>
 * Input: [1,1,1]
 * Output: [false, false, false]
 * Example 3:
 * <p>
 * Input: [0,1,1,1,1,1]
 * Output: [true, false, false, false, true, false]
 * Example 4:
 * <p>
 * Input: [1,1,1,0,1]
 * Output: [false, false, false, false, false]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 30000
 * A[i] is 0 or 1
 * <p>
 * 原文：https://blog.csdn.net/qq_24133491/article/details/88928559
 */
public class PrefixesDivBy5 {

    public static void main(String[] args) {
        System.out.println("[true, false, false] == " + new PrefixesDivBy5().prefixesDivBy5(new int[]{0, 1, 1}));

        System.out.println("[false, false, false] == " + new PrefixesDivBy5().prefixesDivBy5(new int[]{1, 1, 1}));

        System.out.println("[true, false, false, false, true, false] == " + new PrefixesDivBy5().prefixesDivBy5(new int[]{0, 1, 1, 1, 1, 1}));

        System.out.println(new PrefixesDivBy5().prefixesDivBy5(new int[]{1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1}));

        System.out.println(new PrefixesDivBy5().prefixesDivBy5(new int[]{1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0}));

//        [false, false, true, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, true]
//        [false, false, true, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, true, true, true, true, true, true, true, false, false, true, false, false, false, false, true, true]
    }

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int num = 0;
        for (int a : A) {
            num = (num << 1) % 10 + a;
            res.add(num == 0 || num == 5);
        }

        return res;
    }

}
