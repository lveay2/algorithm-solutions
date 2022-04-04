package lintcode.presum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _319_countOfAirplanes {

  public static int countOfAirplanes(List<Interval> airplanes) {
    airplanes.sort(Comparator.comparingInt(a -> a.start));

    PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

    int count = 0;
    for (Interval i : airplanes) {
      while (!minHeap.isEmpty() && i.start > minHeap.peek().end) {
        minHeap.poll();
      }

      minHeap.offer(i);

      count = Math.max(count, minHeap.size());
    }

    return count;
  }

  public static void main(String[] args) {
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval(1, 10));
    intervals.add(new Interval(2, 3));
    intervals.add(new Interval(5, 8));
    intervals.add(new Interval(4, 7));
    System.out.println("3 == " + countOfAirplanes(intervals));
  }

  static class Interval {

    int start, end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
