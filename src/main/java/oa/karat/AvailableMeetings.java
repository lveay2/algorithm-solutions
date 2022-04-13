package oa.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
We are writing a tool to help users manage their calendars. Given an unordered list of times of day when people are busy, write a function that tells us the intervals during the day when ALL of them are available.

Each time is expressed as an integer using 24-hour notation, such as 1200 (12:00), 1530 (15:30), or 800 (8:00).

Sample input:
   // 0 845 930 1230 1300
   // 900 1300 1500

p1_meetings = [
  (1230, 1300),
  ( 845, 900),
  (1300, 1500),
]

p2_meetings = [
  ( 0, 844),
  ( 930, 1200),
  (1515, 1546),
  (1600, 2400),
]

p3_meetings = [
  ( 845, 915),
  (1515, 1545),
  (1235, 1245),
]

p4_meetings = [
  ( 1, 5),
  (844, 900),
  (1515, 1600)
]

schedules1 = [p1_meetings, p2_meetings, p3_meetings]
schedules2 = [p1_meetings, p3_meetings]
schedules3 = [p2_meetings, p4_meetings]

Expected output:

findAvailableTimes(schedules1)
 => [  844,  845 ],
    [  915,  930 ],
    [ 1200, 1230 ],
    [ 1500, 1515 ],
    [ 1546, 1600 ]

findAvailableTimes(schedules2)
 => [    0,  845 ],
    [  915, 1230 ],
    [ 1500, 1515 ],
    [ 1545, 2400 ]

findAvailableTimes(schedules3)
    [  900,  930 ],
    [ 1200, 1515 ]

n = number of meetings
s = number of schedules
*/
public class AvailableMeetings {

    public static void main(String[] argv) {
        int[][] p1Meetings = {
                {1230, 1300},
                {845, 900},
                {1300, 1500}
        };
        int[][] p2Meetings = {
                {0, 844},
                {930, 1200},
                {1515, 1546},
                {1600, 2400}
        };
        int[][] p3Meetings = {
                {845, 915},
                {1515, 1545},
                {1235, 1245}
        };
        int[][] p4Meetings = {
                {1, 5},
                {844, 900},
                {1515, 1600}
        };

        List<int[][]> schedules1 = Arrays.asList(p1Meetings, p2Meetings, p3Meetings);
        List<int[][]> schedules2 = Arrays.asList(p1Meetings, p3Meetings);
        List<int[][]> schedules3 = Arrays.asList(p2Meetings, p4Meetings);

        System.out.println(Arrays.deepToString(findAvailableTimes(schedules1)));
        System.out.println(Arrays.deepToString(findAvailableTimes(schedules2)));
        System.out.println(Arrays.deepToString(findAvailableTimes(schedules3)));
    }

    private static int[][] findAvailableTimes(List<int[][]> meetings) {
        List<int[]> allMeetings = new ArrayList<>();

        List<int[]> ans = new ArrayList<>();
        for (int[][] meeting : meetings) {
            allMeetings.addAll(Arrays.asList(meeting));
        }

        allMeetings.sort(Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(allMeetings.get(0));
        for (int i = 1; i < allMeetings.size(); i++) {
            int[] current = allMeetings.get(i);
            int[] last = merged.get(merged.size() - 1);
            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(current);
            }
        }

        for (int i = 0; i < merged.size() - 1; i++) {
            ans.add(new int[]{merged.get(i)[1], merged.get(i+1)[0]});
        }

        if (merged.get(0)[0] != 0) {
            ans.add(0, new int[]{0, merged.get(0)[0]});
        }
        if (merged.get(merged.size() - 1)[1] != 2400) {
            ans.add(new int[]{merged.get(merged.size() - 1)[1], 2400});
        }

        return ans.toArray(new int[0][0]);
    }

}
