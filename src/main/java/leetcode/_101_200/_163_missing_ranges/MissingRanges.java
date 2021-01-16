package leetcode._101_200._163_missing_ranges;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();

        if (nums == null || nums.length == 0 || nums[nums.length - 1] < lower) {
            return result;
        }

        int start = lower;
        for (int num : nums) {
            if (start > upper) {
                break;
            }

            if (num < start) {
                continue;
            }

            if (num == start) {
                start++;
                continue;
            }

            result.add(getRange(start, num - 1, upper));

            start = num + 1;
        }

        if (start <= upper) {
            result.add(getRange(start, upper, upper));
        }

        return result;
    }

    private static String getRange(int start, int num, int upper) {
        int endNum = Math.min(upper, num);
        return start == num ? String.valueOf(start) : String.format("%d->%d", start, endNum);
    }

    public static List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        int next = lower;
        for (int i = 0; i < nums.length; i++) {
            // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
            if (lower == Integer.MAX_VALUE) {
                return res;
            }
            if (nums[i] < next) {
                continue;
            }
            if (nums[i] == next) {
                next++;
                continue;
            }
            res.add(getRange(next, nums[i] - 1));
            // 2. We don't need to proceed after we have process Integer.MAX_VALUE in array
            if (nums[i] == Integer.MAX_VALUE) {
                return res;
            }
            next = nums[i] + 1;
        }

        if (next <= upper) {
            res.add(getRange(next, upper));
        }
        return res;
    }

    public static String getRange(int n1, int n2) {
        return n1 == n2 ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }

    public static void main(String[] args) {
        System.out.println(
                "[2, 4->49, 51->74, 76->99] == " + findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(
                "[2, 4->49, 51->74, 76] == " + findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 76));
        System.out.println(
                "[2, 4->49, 51->74] == " + findMissingRanges(new int[]{0, 1, 3, 50, 75, 99}, 0, 74));
        System.out.println(
                "[2, 4->49, 51->74] == " + findMissingRanges(new int[]{0, 1, 3, 50, 75, 99}, 2, 74));
        System.out.println(
                "[4->49, 51->74, 76->98, 100] == " + findMissingRanges(new int[]{0, 1, 3, 50, 75, 99}, 3,
                        100));

        System.out.println(
                "[2, 4->49, 51->74, 76->99] == " + findMissingRanges2(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(
                "[2, 4->49, 51->74, 76] == " + findMissingRanges2(new int[]{0, 1, 3, 50, 75}, 0, 76));
        System.out.println(
                "[2, 4->49, 51->74] == " + findMissingRanges2(new int[]{0, 1, 3, 50, 75, 99}, 0, 74));
        System.out.println(
                "[2, 4->49, 51->74] == " + findMissingRanges2(new int[]{0, 1, 3, 50, 75, 99}, 2, 74));
        System.out.println(
                "[4->49, 51->74, 76->98, 100] == " + findMissingRanges2(new int[]{0, 1, 3, 50, 75, 99}, 3,
                        100));
    }

}
