package lintcode.twopointers;

import java.util.Arrays;

public class _1219_Heaters {

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int max = Integer.MIN_VALUE;
        for (int house : houses) {
            int radius = calRadius(house, heaters);
            max = Math.max(max, radius);
        }

        return max;
    }

    private static int calRadius(int house, int[] heaters) {
        int left = 0, right = heaters.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (heaters[mid] == house) {
                return 0;
            } else if (house < heaters[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return Math.min(Math.abs(house - heaters[left]), Math.abs(heaters[right] - house));
    }


    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
    }

}
