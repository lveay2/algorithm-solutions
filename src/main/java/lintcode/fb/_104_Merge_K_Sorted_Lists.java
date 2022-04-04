package lintcode.fb;

import common.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class _104_Merge_K_Sorted_Lists {

  public ListNode mergeKLists(List<ListNode> lists) {
    Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

    ListNode tail = new ListNode(0);
    ListNode dummy = tail;

    for (ListNode node : lists) {
      if (node != null) {
        queue.add(node);
      }
    }

    while (!queue.isEmpty()) {
      ListNode node = queue.poll();
      tail.next = node;
      tail = tail.next;
      if (node.next != null) {
        queue.add(node.next);
      }
    }

    return dummy.next;
  }
}
