package lintcode.fb;

import common.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _30_Insert_Interval {

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        intervals.sort(Comparator.comparingInt(i -> i.start));

        int start = newInterval.start; // 2
        int end = newInterval.end;  // 5
        int l = intervals.size();
        int i = 0;
        while (i < l && intervals.get(i).end < start) {
            result.add(intervals.get(i++));
        }

        while (i < l && intervals.get(i).start <= end) {
            start = Math.min(start, intervals.get(i).start);
            end = Math.max(end, intervals.get(i).end);
            i++;
        }
        result.add(new Interval(start, end));

        while (i < l) {
            result.add(intervals.get(i++));
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(insert(Arrays.asList(new Interval(1, 2), new Interval(5, 9)), new Interval(2, 5)));
        System.out.println(insert(Arrays.asList(new Interval(1, 2), new Interval(5, 9)), new Interval(3, 4)));
        System.out.println(insert(Arrays.asList(new Interval(1, 5)), new Interval(0, 0)));
    }

}
