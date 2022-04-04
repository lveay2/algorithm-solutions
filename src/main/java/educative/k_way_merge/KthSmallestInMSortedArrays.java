package educative.k_way_merge;

import java.util.*;

public class KthSmallestInMSortedArrays {

  public static int findKthSmallest(List<Integer[]> lists, int k) {
    Queue<Node> queue =
        new PriorityQueue<>(Comparator.comparingInt(n -> lists.get(n.arrayIndex)[n.elementIndex]));

    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i) != null) {
        queue.add(new Node(i, 0));
      }
    }

    int result = -1, count = 0;
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      result = lists.get(current.arrayIndex)[current.elementIndex];
      if (++count == k) {
        break;
      }
      int newIndex = ++current.elementIndex;
      if (lists.get(current.arrayIndex).length > newIndex) {
        queue.add(new Node(current.arrayIndex, newIndex));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Integer[] l1 = new Integer[] {2, 6, 8};
    Integer[] l2 = new Integer[] {3, 6, 7};
    Integer[] l3 = new Integer[] {1, 3, 4};
    List<Integer[]> lists = new ArrayList<Integer[]>();
    lists.add(l1);
    lists.add(l2);
    lists.add(l3);
    int result = KthSmallestInMSortedArrays.findKthSmallest(lists, 5);
    System.out.print("Kth smallest number is: " + result);
  }
}
