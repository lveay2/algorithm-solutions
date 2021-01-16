package lintcode.fb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _56_Two_Sum {

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> t2i = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            if (t2i.containsKey(n)) {
                result[0] = t2i.get(n);
                result[1] = i;
                break;
            } else {
                t2i.put(target - n, i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{15, 7, 2, 11}, 9)));
    }

}
