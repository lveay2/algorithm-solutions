package common;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode buildTree(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        String[] values = str.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0, l = values.length;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            i++;
            if (i < l && !values[i].equals("null") && !values[i].equals("#")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(leftNode);
                node.left = leftNode;
            }

            i++;
            if (i < l && !values[i].equals("null") && !values[i].equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(rightNode);
                node.right = rightNode;
            }
        }

        return root;
    }

    // level order traversal using 'next' pointer
    public void printLevelOrder() {
        TreeNode nextLevelRoot = this;
        while (nextLevelRoot != null) {
            TreeNode current = nextLevelRoot;
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

    @Override
    public String toString() {
        printLevelOrder();
        return "";
    }

}
