package leetcode._1_100;

import common.ListNode;

public class _24_swap_nodes_in_pairs {

  public static ListNode swapPairs2(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode prev = dummy;
    ListNode current = head;

    while (current != null && current.next != null) {
      ListNode first = current;
      ListNode second = current.next;

      prev.next = second;
      first.next = second.next;
      second.next = first;

      prev = first;
      current = first.next;
    }

    return dummy.next;
  }

  public static ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode temp = head.next;
    head.next = swapPairs(head.next.next);
    temp.next = head;

    return temp;
  }

  private static void swap(ListNode first, ListNode second) {
    ListNode tempNext = second.next;

    second.next = first;

    first.next = tempNext;
  }

  public static void main(String[] args) {
    ListNode head = createNodes();
    printListNode(head);
    System.out.println();

    printListNode(swapPairs(head));

    System.out.println();

    head = createNodes();
    printListNode(swapPairs2(head));
  }

  private static ListNode createNodes() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    return head;
  }

  private static void printListNode(ListNode current) {
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }
  }
}
