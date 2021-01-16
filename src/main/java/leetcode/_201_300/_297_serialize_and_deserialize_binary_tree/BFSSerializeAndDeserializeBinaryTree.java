package leetcode._201_300._297_serialize_and_deserialize_binary_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        three.left = four;
        three.right = five;
        one.left = two;
        one.right = three;
        TreeNode root = one;
        TreeNode root2 = new TreeNode(1);

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().serialize(root));

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().serialize(root2));

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().serialize(null));

        System.out.println(
                new BFSSerializeAndDeserializeBinaryTree().deserialize("1, 2, 3, null, null, 4, 5"));

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().deserialize("-1, 0, 1"));

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().deserialize("{}"));
    }

    public String serialize(TreeNode root) {
        if (root == null) return "";

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        result.add(root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();

                if (head.left != null) {
                    queue.offer(head.left);
                    result.add(head.left.val);
                } else {
                    result.add(Integer.MIN_VALUE);
                }

                if (head.right != null) {
                    queue.offer(head.right);
                    result.add(head.right.val);
                } else {
                    result.add(Integer.MIN_VALUE);
                }
            }
        }

        while (result.get(result.size() - 1) == Integer.MIN_VALUE) {
            result.remove(result.size() - 1);
        }

        StringBuilder sb = new StringBuilder();

        sb.append(result.get(0));

        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) == Integer.MIN_VALUE) {
                sb.append(", null");
            } else {
                sb.append(", " + result.get(i));
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) throws NumberFormatException {
        if (data == null || data.length() == 0 || data.equals("{}")) return null;

        Queue<TreeNode> queue = new LinkedList<>();

        String[] values = data.split(",");

        int l = values.length;

        TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));

        queue.offer(root);

        int i = 1;

        while (!queue.isEmpty() && i < l) {

            TreeNode head = queue.poll();

            if (i < l && !values[i].trim().equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i].trim()));

                head.left = left;

                queue.offer(left);
            }

            i++;

            if (i < l && !values[i].trim().equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i].trim()));

                head.right = right;

                queue.offer(right);
            }

            i++;
        }

        System.out.println(new BFSSerializeAndDeserializeBinaryTree().serialize(root));

        return root;
    }

}
