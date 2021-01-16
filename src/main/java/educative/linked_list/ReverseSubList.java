package educative.linked_list;

import educative.fast_slow_pointers.ListNode;

/**
 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from
 * position ‘p’ to ‘q’.
 */
public class ReverseSubList {

    public static ListNode reverse(ListNode head, int p, int q) {
        if (p == q) {
            return head;
        }

        ListNode current = head, prev = null;
        for (int i = 0; current != null && i < p - 1; i++) {
            prev = current;
            current = current.next;
        }

        ListNode firstLastNode = prev;
        ListNode reversedLastNode = current;

        int c = q - p + 1;
        while (current != null && c > 0) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            c--;
        }

        if (firstLastNode == null) {
            head = prev;
        } else {
            firstLastNode.next = prev;
        }
        reversedLastNode.next = current;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = ReverseSubList.reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: 1 4 3 2 5 == ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

}
