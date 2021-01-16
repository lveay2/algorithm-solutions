package leetcode._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _56_Merge_Intervals {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] n : intervals) {
            if (n[0] <= end) {
                end = Math.max(end, n[1]);
            } else {
                result.add(new int[]{start, end});
                start = n[0];
                end = n[1];
            }
        }
        result.add(new int[]{start, end});

        return result.toArray(new int[0][0]);
    }

    public static int[][] merge2(int[][] intervals) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int tempStart = intervals[i][0];
            int tempEnd = intervals[i][1];
            if (tempStart <= end) {
                end = Math.max(end, tempEnd);
            } else {
                result.add(Arrays.asList(start, end));
                start = tempStart;
                end = tempEnd;
            }
        }
        result.add(Arrays.asList(start, end));

        return result.stream().map(u -> u.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
    }

    public static void main(String[] args) {
        System.out.println(
                "[[1, 6], [8, 10], [15, 18]] == "
                        + Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println("[[1, 5]] == " + Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
        System.out.println("[[0, 4]] == " + Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 4}})));

        int[][] test = new int[][]{{1, 4}, {0, 4}};
        int[][] test2 = new int[][]{{1, 4}, {0, 5}, {2, 3}, {1, 1}};
        Arrays.sort(test, Comparator.comparingInt(i -> i[0]));
        System.out.println(Arrays.deepToString(test));
        Arrays.sort(test2, Comparator.comparingInt(i -> i[1]));
        System.out.println(Arrays.deepToString(test2));
    }

}
