package educative.two_heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Design a class to calculate the median of a number stream. The class should have the following
 * two methods:
 * <p>
 * insertNum(int num): stores the number in the class findMedian(): returns the median of all
 * numbers inserted in the class If the count of numbers inserted in the class is even, the median
 * will be the average of the middle two numbers.
 * <p>
 * Example 1:
 * <p>
 * 1. insertNum(3) 2. insertNum(1) 3. findMedian() -> output: 2 4. insertNum(5) 5. findMedian() ->
 * output: 3 6. insertNum(4) 7. findMedian() -> output: 3.5
 */
public class MedianOfAStream {

    PriorityQueue<Integer> maxHeap; //containing first half of numbers
    PriorityQueue<Integer> minHeap; //containing second half of numbers

    public MedianOfAStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: 2.0 == " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: 3.0 == " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: 3.5 == " + medianOfAStream.findMedian());
    }

    public void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // we have even number of elements, take the average of middle two elements
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        // because max-heap will have one more element than the min-heap
        return maxHeap.peek();
    }

}
