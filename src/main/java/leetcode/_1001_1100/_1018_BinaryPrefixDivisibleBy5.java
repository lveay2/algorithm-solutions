package leetcode._1001_1100;

import java.util.ArrayList;
import java.util.List;

/*
You are given a binary array nums (0-indexed).

We define xi as the number whose binary representation is the
subarray nums[0..i] (from most-significant-bit to least-significant-bit).

For example, if nums = [1,0,1], then x0 = 1, x1 = 2, and x2 = 5.
Return an array of booleans answer where answer[i] is true if xi is divisible by 5.

Example 1:
Input: nums = [0,1,1]
Output: [true,false,false]
Explanation: The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
Only the first number is divisible by 5, so answer[0] is true.

Example 2:
Input: nums = [1,1,1]
Output: [false,false,false]

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
*/
public class _1018_BinaryPrefixDivisibleBy5 {

    public static void main(String[] args) {
        System.out.println("[true, false, false] == \n" + prefixesDivBy5(new int[]{0, 1, 1}));
        System.out.println("[false, false, false] == \n" + prefixesDivBy5(new int[]{1, 1, 1}));
        System.out.println("[true, false, false, false, true, false] == \n"
                + prefixesDivBy5(new int[]{0, 1, 1, 1, 1, 1}));
    }

    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int num = 0;
        for (int a : A) {
            num = (num << 1) % 10 + a;
            res.add(num == 0 || num == 5);
        }

        return res;
    }

}
