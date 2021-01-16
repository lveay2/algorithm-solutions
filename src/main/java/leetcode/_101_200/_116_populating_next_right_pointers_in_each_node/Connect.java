package leetcode._101_200._116_populating_next_right_pointers_in_each_node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Example:
 *      1
 *    /   \
 *    2    3
 *   / \   / \
 *  4   5 6   7
 *
 *      1
 *    /   \
 *    2 -> 3
 *   / \   / \
 *  4 ->5 6 -> 7
 *
 *
 * Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 *
 * Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 *
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 *
 *
 * Note:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 *
 */
public class Connect {

    private static Node deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        String[] trees = str.split(",");

        Queue<Node> queue = new LinkedList<>();

        Node root = new Node(Integer.parseInt(trees[0]));

        queue.offer(root);

        int l = trees.length;

        int n = 0;

        while (!queue.isEmpty() && n < l) {
            Node head = queue.poll();

            n++;

            if (n < l && !trees[n].equals("null")) {
                Node left = new Node(Integer.parseInt(trees[n]));

                queue.offer(left);

                head.left = left;
            }

            n++;

            if (n < l && !trees[n].equals("null")) {
                Node right = new Node(Integer.parseInt(trees[n]));

                queue.offer(right);

                head.right = right;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        new Connect().connect(deserialize("1,2,3,4,5,6,7"));
    }

    public Node connect(Node root) {
        List<List<String>> print = new ArrayList<>();

        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node head = queue.poll();

                if (i + 1 < size) {
                    head.next = queue.peek();
                }

                if (head.left != null) {
                    queue.offer(head.left);
                }

                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
        }

        return root;
    }

}
