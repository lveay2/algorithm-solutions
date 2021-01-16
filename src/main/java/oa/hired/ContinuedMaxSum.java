package oa.hired;

public class ContinuedMaxSum {

    public static long calculate(long[] numbers) {
        long max = 0L;
        long sum = 0L;
        for (int end = 0; end < numbers.length; end++) {
            long en = numbers[end];
            sum += en;

            if (end > 0 && numbers[end - 1] != en + 1) {
                sum = numbers[end];
            }

            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("15 == " + calculate(new long[]{9, 5, 12, 6, 5, 4, 8}));
    }

}
