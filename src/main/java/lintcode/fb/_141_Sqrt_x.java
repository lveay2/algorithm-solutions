package lintcode.fb;

public class _141_Sqrt_x {

    public static int sqrt(int x) {
        if (x <= 0) {
            return 0;
        }

        int left = 1, right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right * right == x) return right;
        return left;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(0));
        System.out.println(sqrt(3));
        System.out.println(sqrt(4));
        System.out.println(sqrt(Integer.MAX_VALUE));
    }

}
