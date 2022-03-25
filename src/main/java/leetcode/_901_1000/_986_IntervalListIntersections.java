package leetcode._901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
986. Interval List Intersections

You are given two lists of closed intervals, firstList and
secondList, where firstList[i] = [starti, endi] and
secondList[j] = [startj, endj]. Each list of intervals is
pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of
real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real
numbers that are either empty or represented as a closed
interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

Example 1:
Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

Example 2:
Input: firstList = [[1,3],[5,9]], secondList = []
Output: []

Constraints:
0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 109
endi < starti+1
0 <= startj < endj <= 109
endj < startj+1
 */
public class _986_IntervalListIntersections {

    public static void main(String[] args) {
        System.out.println("[[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]] == \n"
                + Arrays.deepToString(intervalIntersection(
                new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}})));

        System.out.println("[] == " + Arrays.deepToString(
                intervalIntersection(new int[][]{{1, 3}, {5, 9}}, new int[0][0])));
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length;
        int n = secondList.length;
        List<int[]> ans = new ArrayList<>();

        int i = 0, j = 0;
        while (i < m && j < n) {
            int a1 = firstList[i][0], a2 = firstList[i][1];
            int b1 = secondList[j][0], b2 = secondList[j][1];

            if (b2 >= a1 && a2 >= b1) {
                int start = Math.max(a1, b1);
                int end = Math.min(a2, b2);
                ans.add(new int[]{start, end});
            }

            if (a2 > b2) {
                j++;
            } else {
                i++;
            }
        }

        return ans.toArray(int[][]::new);
    }

}
