package educative.merge_intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum
 * number of rooms required to hold all the meetings.
 * <p>
 * Example 1:
 * <p>
 * Meetings: [[1,4], [2,5], [7,9]] Output: 2 Explanation: Since [1,4] and [2,5] overlap, we need two
 * rooms to hold these two meetings. [7,9] can occur in any of the two rooms later.
 * <p>
 * Example 2:
 * <p>
 * Meetings: [[6,7], [2,4], [8,12]] Output: 1 Explanation: None of the meetings overlap, therefore
 * we only need one room to hold all meetings.
 * <p>
 * Example 3:
 * <p>
 * Meetings: [[1,4], [2,3], [3,6]] Output:2 Explanation: Since [1,4] overlaps with the other two
 * meetings [2,3] and [3,6], we need two rooms to hold all the meetings.
 * <p>
 * Example 4:
 * <p>
 * Meetings: [[4,5], [2,3], [2,4], [3,5]] Output: 2 Explanation: We will need one room for [2,3] and
 * [3,5], and another room for [2,4] and [4,5].
 */
public class MinimumMeetingRooms {

    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.size() == 0) {
            return 0;
        }

        // sort the meetings by start time
        meetings.sort(Comparator.comparingInt(a -> a.start));

        int minRooms = 0;
        PriorityQueue<Meeting> minHeap = new PriorityQueue<>(meetings.size(),
                Comparator.comparingInt(a -> a.end));
        for (Meeting meeting : meetings) {
            // remove all meetings that have ended
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll();
            }
            // add the current meeting into the minHeap
            minHeap.offer(meeting);
            // all active meeting are in the minHeap, so we need rooms for all of them.
            minRooms = Math.max(minRooms, minHeap.size());
        }
        return minRooms;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }

}
