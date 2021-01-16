package oa.fb_product_except_self;

import java.util.Arrays;

/**
 * Write a function that given an array of N numbers returns an array of N
 * numbers, where for each index i, the resulting array will have the product
 * of all numbers in the original array, except for the one at index i.
 *
 * [3, 6, 5] => [30, 15, 18]
 * [3, 0, 5] => [0, 15, 0]
 *
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
//        System.out.println("[] == " + Arrays.toString(new ProductExceptSelf().product(new int[0])));
//        System.out.println("[30, 15, 18] == " + Arrays.toString(new ProductExceptSelf().product(new int[]{3, 6, 5})));
//        System.out.println("[0, 15, 0] == " + Arrays.toString(new ProductExceptSelf().product(new int[]{3, 0, 5})));
//        System.out.println("[0, 0, 0, 0] == " + Arrays.toString(new ProductExceptSelf().product(new int[]{3, 0, 0, 5})));

//        System.out.println("[] == " + Arrays.toString(new ProductExceptSelf().productExceptSelf(new int[0])));
        System.out.println("[30, 15, 18] == " + Arrays.toString(new ProductExceptSelf().productExceptSelf(new int[]{3, 6, 5})));
        System.out.println("[0, 15, 0] == " + Arrays.toString(new ProductExceptSelf().productExceptSelf(new int[]{3, 0, 5})));
        System.out.println("[0, 0, 0, 0] == " + Arrays.toString(new ProductExceptSelf().productExceptSelf(new int[]{3, 0, 0, 5})));
    }

    public int[] product(int[] nums) {
        int[] result = new int[nums.length];

        if (nums == null || nums.length == 0) return result;

        int l = result.length, product = 1, index = -1, count = 0;

        for (int i = 0; i < l; i++) {
            if (nums[i] == 0) {
                count++;

                if (index < 0) index = i;
            } else {
                product *= nums[i];
            }
        }

        if (count == 0) {
            for (int i = 0; i < l; i++) {
                result[i] = product / nums[i];
            }
        } else if (count == 1) {
            result[index] = product;
        }

        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        int zero = nums[0] == 0 ? 0 : -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                if (zero >= 0) {
                    // having two zeros
                    return new int[n];
                }
                zero = i;
            }
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }


}
