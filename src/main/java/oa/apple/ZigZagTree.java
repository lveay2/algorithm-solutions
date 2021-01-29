package oa.apple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Print a tree in zig-zag form, for example:
 *
 *     1
 *    /| \
 *   2 3  4
 *  /  |  | \
 * 5   6  7  8
 *
 */
public class ZigZagTree {

    private static List<Integer> printZigZagTree(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        boolean flag = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                if (flag) {
                    currentLevel.add(current.value);
                } else {
                    currentLevel.add(0, current.value);
                }

                for (Node child : current.children) {
                    queue.offer(child);
                }
            }
            flag = !flag;

            result.addAll(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);

        one.children.add(two);
        one.children.add(three);
        one.children.add(four);

        two.children.add(five);
        three.children.add(six);
        four.children.add(seven);
        four.children.add(eight);

        eight.children.add(nine);
        eight.children.add(ten);
        eight.children.add(eleven);

        System.out.println(printZigZagTree(one));
        System.out.println(printZigZagTree(null));
    }

    static class Node {

        int value;
        List<Node> children;

        Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

    }

}
