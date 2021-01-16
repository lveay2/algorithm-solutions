package oa.fb_find_x;

/**
 * Given a sorted int array which might have repeated numbers and a digit K, return the count of digit K in the array.
 * <p>
 * e.g., for [1, 2, 3, 7, 7, 7, 9, 9]
 * <p>
 * K = 1 => 1
 * K = 7 => 3
 * K = 9 => 2
 */
public class CountX {

    public static void main(String[] args) {
        System.out.println("3 == " + new CountX().countX(new int[]{1, 2, 3, 7, 7, 7, 9, 9}, 7));
        System.out.println("1 == " + new CountX().countX(new int[]{1, 2, 3, 7, 7, 7, 9, 9}, 3));
        System.out.println("2 == " + new CountX().countX(new int[]{1, 2, 3, 7, 7, 7, 9, 9}, 9));
        System.out.println("2 == " + new CountX().countX(new int[]{1, 1, 2, 3, 7, 7, 7, 9, 9}, 1));

        System.out.println("3 == " + new CountX().countX(new int[]{1, 2, 2, 2, 4, 5, 9}, 2));
        System.out.println("1 == " + new CountX().countX(new int[]{1, 2, 2, 2, 4, 5, 9}, 9));
        System.out.println("1 == " + new CountX().countX(new int[]{1, 2, 2, 2, 4, 5, 9}, 5));
        System.out.println("1 == " + new CountX().countX(new int[]{1, 2, 2, 2, 4, 5, 9}, 1));

        System.out.println("6 == " + new CountX().countX(new int[]{1, 1, 1, 1, 1, 1}, 1));

        System.out.println("6 == " + new CountX().countX(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, Integer.MAX_VALUE));

        System.out.println("0 == " + new CountX().countX(new int[0], 1));
    }

    public int countX(int[] array, int x) {
        if (array == null || array.length == 0) return 0;

        int l = array.length;

        if (x > array[l - 1] || x < array[0]) {
            return 0;
        }

        int left = findFirst(array, 0, l - 1, x);

        if (left == -1) return 0;

        int right = findEnd(array, left, l - 1, x);

        return right - left + 1;
    }

    private int findFirst(int[] array, int start, int end, int x) {
        int result = -1;

        while (start <= end) {
            if (array[start] == x) {
                return start;
            }

            int mid = start + (end - start) / 2;

            if (array[mid] == x) {
                end = mid - 1;

                result = mid;
            } else if (x > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }

    private int findEnd(int[] array, int start, int end, int x) {
        int result = -1;

        while (start <= end) {
            if (array[end] == x) {
                return end;
            }

            int mid = start + (end - start) / 2;

            if (x == array[mid]) {
                start = mid + 1;

                result = mid;
            } else if (x > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }

}
