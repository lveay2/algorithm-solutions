package leetcode._101_200;

import common.Node;

import java.util.LinkedList;
import java.util.Queue;

public class _117_Populating_Next_Right_Pointers_in_Each_Node_II {

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (i < size - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        connect(Node.buildTree("1,2,3,4,5,null,7")).printLevelOrder();
    }

}
