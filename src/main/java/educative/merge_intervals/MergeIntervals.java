package educative.merge_intervals;

import java.util.*;

/**
 * Given a list of intervals, merge all the overlapping intervals to produce a list that has only
 * mutually exclusive intervals.
 * <p>
 * Example 1:
 * <p>
 * Intervals: [[1,4], [2,5], [7,9]] Output: [[1,5], [7,9]] Explanation: Since the first two
 * intervals [1,4] and [2,5] overlap, we merged them into one [1,5].
 * <p>
 * Example 2:
 * <p>
 * Intervals: [[6,7], [2,4], [5,9]] Output: [[2,4], [5,9]] Explanation: Since the intervals [6,7]
 * and [5,9] overlap, we merged them into one [5,9].
 * <p>
 * Example 3:
 * <p>
 * Intervals: [[1,4], [2,6], [3,5]] Output: [[1,6]] Explanation: Since all the given intervals
 * overlap, we merged them into one.
 */
public class MergeIntervals {

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new LinkedList<>();

        if (intervals.size() < 2) {
            return intervals;
        }

        intervals.sort(Comparator.comparingInt(a -> a.start));

        Iterator<Interval> intervalsIterator = intervals.iterator();
        Interval first = intervalsIterator.next();
        int start = first.start;
        int end = first.end;

        while (intervalsIterator.hasNext()) {
            Interval next = intervalsIterator.next();
            if (next.start < end) {
                end = Math.max(end, next.end);
            } else {
                mergedIntervals.add(new Interval(start, end));
                start = next.start;
                end = next.end;
            }
        }
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input)) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input)) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input)) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();
    }

}
