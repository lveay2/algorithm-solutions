package common;

import java.util.LinkedList;
import java.util.Queue;

public class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int x) {
        val = x;
    }

    public static Node buildTree(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        String[] values = str.split(",");

        Node root = new Node(Integer.parseInt(values[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0, l = values.length;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            i++;
            if (i < l && !values[i].equals("null")) {
                Node leftNode = new Node(Integer.parseInt(values[i]));
                queue.offer(leftNode);
                node.left = leftNode;
            }

            i++;
            if (i < l && !values[i].equals("null")) {
                Node rightNode = new Node(Integer.parseInt(values[i]));
                queue.offer(rightNode);
                node.right = rightNode;
            }
        }

        return root;
    }

    // level order traversal using 'next' pointer
    public void printLevelOrder() {
        Node nextLevelRoot = this;
        while (nextLevelRoot != null) {
            Node current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
                System.out.print(current.val + " ");
                if (nextLevelRoot == null) {
                    if (current.left != null) {
                        nextLevelRoot = current.left;
                    } else if (current.right != null) {
                        nextLevelRoot = current.right;
                    }
                }
                current = current.next;
            }
            System.out.println();
        }
    }

}
