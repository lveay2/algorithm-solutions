package educative.fast_slow_pointers;

/**
 * Given the head of a Singly LinkedList, write a method to return the middle node of the
 * LinkedList.
 *
 * <p>If the total number of nodes in the LinkedList is even, return the second middle node.
 *
 * <p>Example 1:
 *
 * <p>Input: 1 -> 2 -> 3 -> 4 -> 5 -> null Output: 3
 *
 * <p>Example 2:
 *
 * <p>Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null Output: 4
 *
 * <p>Example 3:
 *
 * <p>Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null Output: 4
 */
public class MiddleOfLinkedList {

  public static ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

    head.next.next.next.next.next = new ListNode(6);
    System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

    head.next.next.next.next.next.next = new ListNode(7);
    System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);
  }
}
