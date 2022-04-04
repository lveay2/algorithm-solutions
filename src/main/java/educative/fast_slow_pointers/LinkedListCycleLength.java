package educative.fast_slow_pointers;

public class LinkedListCycleLength {

  public static int findCycleLength(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (slow == fast) {
        return calculateLength(slow);
      }
    }
    return 0;
  }

  private static int calculateLength(ListNode slow) {
    ListNode current = slow;
    int count = 0;
    do {
      current = current.next;
      count++;
    } while (current != slow);

    return count;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));

    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));
  }
}
