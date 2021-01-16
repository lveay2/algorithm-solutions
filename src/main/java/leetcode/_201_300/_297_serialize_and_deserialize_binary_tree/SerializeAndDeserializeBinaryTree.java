package leetcode._201_300._297_serialize_and_deserialize_binary_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 *
 * <p>Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work. You just need to ensure that a binary
 * tree can be serialized to a string and this string can be deserialized to the original tree
 * structure.
 *
 * <p>Example:
 *
 * <p>You may serialize the following tree:
 *
 * <p>1 / \ 2 3 / \ 4 5
 *
 * <p>as "[1,2,3,null,null,4,5]" Clarification: The above format is the same as how LeetCode
 * serializes a binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 *
 * <p>Note: Do not use class member/global/static variables to store states. Your serialize and
 * deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

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

        System.out.println(new SerializeAndDeserializeBinaryTree().serialize(root));

        System.out.println(
                new SerializeAndDeserializeBinaryTree().deserialize("1, 2, 3, null, null, 4, 5"));

        System.out.println(new SerializeAndDeserializeBinaryTree().deserialize("-1, 0, 1"));

        System.out.println(new SerializeAndDeserializeBinaryTree().deserialize(""));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        List<TreeNode> list = new ArrayList<>();

        list.add(root);

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);

            if (node == null) {
                continue;
            }

            list.add(node.left);

            list.add(node.right);
        }

        while (list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);

            if (node == null) {
                sb.append(", null");
            } else {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(node.val);
            }
        }
        sb.append("}");

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("")) {
            return null;
        }

        String[] vals = data.split(",");

        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();

        TreeNode root = new TreeNode(Integer.parseInt(vals[0].trim()));

        queue.add(root);

        int index = 0;

        boolean isLeftChild = true;

        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].trim().equals("null")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i].trim()));

                if (isLeftChild) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }

                queue.add(node);
            }

            if (!isLeftChild) {
                index++;
            }

            isLeftChild = !isLeftChild;
        }

        System.out.println(new SerializeAndDeserializeBinaryTree().serialize(root));

        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
