package leetcode._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
56. Merge Intervals

Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping
intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
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
        result.add(new int[] {start, end});
        start = n[0];
        end = n[1];
      }
    }
    result.add(new int[] {start, end});

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
            + Arrays.deepToString(merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    System.out.println("[[1, 5]] == " + Arrays.deepToString(merge(new int[][] {{1, 4}, {4, 5}})));
    System.out.println("[[0, 4]] == " + Arrays.deepToString(merge(new int[][] {{1, 4}, {0, 4}})));

    System.out.println(
        "[[1, 6], [8, 10], [15, 18]] == "
            + Arrays.deepToString(merge3(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    System.out.println("[[1, 5]] == " + Arrays.deepToString(merge3(new int[][] {{1, 4}, {4, 5}})));
    System.out.println("[[0, 4]] == " + Arrays.deepToString(merge3(new int[][] {{1, 4}, {0, 4}})));

    int[][] test = new int[][] {{1, 4}, {0, 4}};
    int[][] test2 = new int[][] {{1, 4}, {0, 5}, {2, 3}, {1, 1}};
    Arrays.sort(test, Comparator.comparingInt(i -> i[0]));
    System.out.println(Arrays.deepToString(test));
    Arrays.sort(test2, Comparator.comparingInt(i -> i[1]));
    System.out.println(Arrays.deepToString(test2));
  }

  // preferred
  public static int[][] merge3(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> result = new ArrayList<>();
    result.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
      int[] current = intervals[i];

      int[] last = result.get(result.size() - 1);
      if (current[0] <= last[1]) {
        last[1] = Math.max(last[1], current[1]);
        continue;
      }

      result.add(current);
    }

    return result.toArray(int[][]::new);
  }
}
