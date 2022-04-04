package educative.k_way_merge;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

  public static ListNode merge(ListNode[] lists) {
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

    // put the root of each list in the min heap
    for (ListNode root : lists) {
      if (root != null) {
        minHeap.add(root);
      }
    }

    ListNode result = new ListNode(-1);
    ListNode tail = result;

    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();
      tail.next = node;
      tail = tail.next;
      if (node.next != null) {
        minHeap.add(node.next);
      }
    }

    return result.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(6);
    l1.next.next = new ListNode(8);

    ListNode l2 = new ListNode(3);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(7);

    ListNode l3 = new ListNode(1);
    l3.next = new ListNode(3);
    l3.next.next = new ListNode(4);

    ListNode result = MergeKSortedLists.merge(new ListNode[] {l1, l2, l3});
    System.out.print("Here are the elements form the merged list: ");
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }
}
