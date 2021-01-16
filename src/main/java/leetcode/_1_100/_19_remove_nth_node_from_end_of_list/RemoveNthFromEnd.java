package leetcode._1_100._19_remove_nth_node_from_end_of_list;

public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);

        ListNode two = new ListNode(2);

        ListNode three = new ListNode(3);

        ListNode four = new ListNode(4);

        ListNode five = new ListNode(5);

        one.next = two;

        two.next = three;

        three.next = four;

        four.next = five;

        ListNode head = new RemoveNthFromEnd().removeNthFromEnd(one, 2);

        printNode(head);

        two.next = null;

        ListNode head2 = new RemoveNthFromEnd().removeNthFromEnd(one, 1);

        printNode(head2);

    }

    private static void printNode(ListNode head) {
        StringBuilder sb = new StringBuilder(Integer.toString(head.val));

        while (head.next != null) {
            head = head.next;

            sb.append("->").append(head.val);
        }

        System.out.println(sb.toString());

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prenode = head;

        ListNode curnode = head;

        for (int i = 0; i < n; i++) {
            if (curnode == null) {
                break;
            }

            curnode = curnode.next;
        }

        if (curnode == null) {
            return head.next;
        }

        while (curnode.next != null) {
            prenode = prenode.next;

            curnode = curnode.next;
        }

        prenode.next = prenode.next.next;

        return head;
    }

}

class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }

}