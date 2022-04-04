package leetcode._1_100;

import common.ListNode;

import java.util.*;

public class _23_Merge_k_Sorted_Lists {

  public static ListNode mergeKLists(ListNode[] lists) {
    Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

    ListNode tail = new ListNode(0);
    ListNode dummy = tail;

    for (ListNode node : lists) {
      if (node != null) {
        queue.add(node);
      }
    }

    while (!queue.isEmpty()) {
      ListNode currentNode = queue.poll();
      tail.next = currentNode;
      tail = tail.next;
      if (tail.next != null) {
        queue.add(tail.next);
      }
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode node_1 = new ListNode(1);
    ListNode node_2 = new ListNode(2);
    ListNode node_3 = new ListNode(3);
    ListNode node_4 = new ListNode(4);
    ListNode node_5 = new ListNode(5);
    ListNode node_6 = new ListNode(6);

    ListNode node_1_1 = node_1;
    node_1_1.next = node_4;
    node_4.next = node_5;

    ListNode node_1_2 = new ListNode(1, node_3);
    node_3.next = new ListNode(4);

    node_2.next = node_6;

    System.out.println(printNodeList(mergeKLists(new ListNode[] {node_1_1, node_1_2, node_2})));
  }

  private static List<Integer> printNodeList(ListNode node) {
    ListNode head = node;
    List<Integer> list = new ArrayList<>();
    list.add(head.val);
    while (head.next != null) {
      list.add(head.next.val);
      head = head.next;
    }
    return list;
  }
}
