package leetcode._201_300;

import java.util.Arrays;

/*
253. Meeting Rooms II
Given an array of meeting time intervals intervals where
intervals[i] = [starti, endi], return the minimum number
of conference rooms required.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1

Constraints:
1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */
public class _253_MeetingRoomsII {

  public static void main(String[] args) {
    System.out.println("2 == " + minMeetingRooms(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
    System.out.println("1 == " + minMeetingRooms(new int[][] {{7, 10}, {2, 4}}));
  }

  public static int minMeetingRooms(int[][] intervals) {
    int n = intervals.length;
    int[] begins = new int[n];
    int[] ends = new int[n];

    for (int i = 0; i < n; i++) {
      begins[i] = intervals[i][0];
      ends[i] = intervals[i][1];
    }

    Arrays.sort(begins);
    Arrays.sort(ends);

    int ans = 0, count = 0;
    int i = 0, j = 0;
    while (i < n && j < n) {
      if (begins[i] < ends[j]) {
        count++;
        i++;
      } else {
        count--;
        j++;
      }
      ans = Math.max(ans, count);
    }

    return ans;
  }
}
