package leetcode._1201_1300;

import java.util.Arrays;

/*
1288. Remove Covered Intervals
Given an array intervals where intervals[i] = [li, ri]
represent the interval [li, ri), remove all intervals
that are covered by another interval in the list.

The interval [a, b) is covered by the interval [c, d)
if and only if c <= a and b <= d.

Return the number of remaining intervals.

Example 1:
Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

Example 2:
Input: intervals = [[1,4],[2,3]]
Output: 1

Constraints:
1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= li < ri <= 105
All the given intervals are unique.
 */
public class _1288_RemoveCoveredIntervals {

    public static void main(String[] args) {
        System.out.println("2 == " + removeCoveredIntervals(
                new int[][]{{1, 4}, {3, 6}, {2, 8}})
        );
        System.out.println("1 == " + removeCoveredIntervals(
                new int[][]{{1, 4}, {2, 3}})
        );
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int start = intervals[0][0];
        int end = intervals[0][1];

        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // cover
            if (start <= intv[0] && end >= intv[1]) {
                ans++;
                continue;
            }

            // partically overlap
            if (end >= intv[0] && end <= intv[1]) {
                end = intv[1];
            }

            // no overlap
            if (end < intv[1]) {
                start = intv[0];
                end = intv[1];
            }
        }

        return intervals.length - ans;
    }

}
