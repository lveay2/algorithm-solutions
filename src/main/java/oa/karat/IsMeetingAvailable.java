package oa.karat;

public class IsMeetingAvailable {

    public static void main(String[] args) {
        System.out.println(isAvailable(new int[][]{{900, 1200}}, 800, 900));
        System.out.println(isAvailable(new int[][]{{900, 1200}}, 800, 1000));
        System.out.println(isAvailable(new int[][]{{900, 1200}}, 1000, 1100));
        System.out.println(isAvailable(new int[][]{{900, 1200},{1250, 1600}}, 1300, 1500));
    }

    private static boolean isAvailable(int[][] meetings, int start, int end) {
        int ml = meetings.length;
        int[][] m = new int[ml][2];
        for (int i = 0; i < ml; i++) {
            m[i] = meetings[i];
        }

        for (int[] intv: m) {
            // start end              start end
            //            0          1
            if (end <= intv[0] || intv[1] <= start) {
                continue;
            }

            // start                end       start      end
            //          0      1                     0          1
            //     start   end                start   end
            //  0                  1   0            1
            //
            if (intv[1] > start && end > intv[0]) {
                return false;
            }
        }

        return true;
    }
}
