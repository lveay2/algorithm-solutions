package lintcode.presum;

public class _62_search {

    public static int search(int[] a, int target) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int start = 0;
        int end = a.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (a[mid] == target) {
                return mid;
            }

            if (a[start] < a[mid]) {
                if (a[start] <= target && target <= a[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (a[mid] <= target && target <= a[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (a[start] == target) {
            return start;
        }
        if (a[end] == target) {
            return end;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 1, 2, 3}, 1));
        System.out.println(search(new int[]{4, 5, 1, 2, 3}, 0));
        System.out.println(search(new int[]{5}, 5));
    }

}
