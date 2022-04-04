package leetcode._201_300._238_product_of_array_except_self;

public class ProductExceptSelf {

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

    if (count == 0) {}

    return result;
  }
}
