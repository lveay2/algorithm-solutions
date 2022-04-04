package leetcode._401_500;

import java.util.Arrays;
import java.util.Comparator;

/*
435. Non-overlapping Intervals
Given an array of intervals intervals where intervals[i] = [starti, endi],
return the minimum number of intervals you need to remove to make the rest
of the intervals non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
public class _435_NonOverlappingIntervals {

    public static void main(String[] args) {
        System.out.println("1 == " + eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 2},
        }));
        System.out.println("2 == " + eraseOverlapIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));
        System.out.println("0 == " + eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}
        }));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = findNonOverlappingIntervals(intervals);
        return (intervals.length - n);
    }

    private static int findNonOverlappingIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int end = intervals[0][1];
        int count = 1;

        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];

            if (intv[0] >= end) {
                count++;
                end = intv[1];
            }
        }

        return count;
    }

}
