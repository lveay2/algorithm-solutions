package leetcode._201_300._297_serialize_and_deserialize_binary_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSerializeAndDeserializeBinaryTreeCopy {

    public static String serialize(TreeNode root) throws NumberFormatException {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        List<String> list = new ArrayList<>();

        list.add(Integer.toString(root.val));

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();

                if (head.left != null) {
                    queue.offer(head.left);
                    list.add(Integer.toString(head.left.val));
                } else {
                    list.add("null");
                }

                if (head.right != null) {
                    queue.offer(head.right);
                    list.add(Integer.toString(head.right.val));
                } else {
                    list.add("null");
                }
            }
        }

        while (list.get(list.size() - 1).equals("null")) {
            list.remove(list.size() - 1);
        }

        return String.join(",", list);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("{}")) {
            return null;
        }

        String[] datas = data.split(",");

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));

        queue.offer(root);

        int i = 0;

        int l = datas.length;

        while (!queue.isEmpty() && i < l) {
            i++;

            TreeNode head = queue.poll();

            if (i < l && !datas[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(datas[i]));

                head.left = left;

                queue.offer(left);
            }

            i++;

            if (i < l && !datas[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(datas[i]));

                head.right = right;

                queue.offer(right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("1,2,3,null,null,4,5");
        TreeNode root2 = new TreeNode(1);

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.serialize(root));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.serialize(root2));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.serialize(null));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.deserialize("1,2,3,null,null,4,5"));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.deserialize("-1,0,1"));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.deserialize("{}"));

        System.out.println(BFSSerializeAndDeserializeBinaryTreeCopy.serialize(BFSSerializeAndDeserializeBinaryTreeCopy.deserialize("1,2,3,null,null,4,5")));
    }

}
