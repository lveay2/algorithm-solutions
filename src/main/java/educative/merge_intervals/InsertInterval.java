package educative.merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at
 * the correct position and merge all necessary intervals to produce a list that has only mutually
 * exclusive intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6] Output: [[1,3], [4,7], [8,12]]
 * Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
 * <p>
 * Example 2:
 * <p>
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10] Output: [[1,3], [4,12]] Explanation:
 * After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].
 * <p>
 * Example 3:
 * <p>
 * Input: Intervals=[[2,3],[5,7]], New Interval=[1,4] Output: [[1,4], [5,7]] Explanation: After
 * insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 */
public class InsertInterval {

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) {
            return Collections.singletonList(newInterval);
        }

        List<Interval> mergedIntervals = new ArrayList<>();
        int start = newInterval.start;
        int end = newInterval.end;

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < start) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }

        while (i < intervals.size() && intervals.get(i).start <= end) {
            start = Math.min(start, intervals.get(i).start);
            end = Math.max(end, intervals.get(i).end);
            i++;
        }
        mergedIntervals.add(new Interval(start, end));

        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i++));
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 6))) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 10))) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(1, 4))) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();
    }

}
