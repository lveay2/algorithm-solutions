package leetcode._101_200;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
144. Binary Tree Preorder Traversal
Given the root of a binary tree, return the
preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class _144_BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        System.out.println("[1, 2, 3] == " + preorderTraversal(TreeNode.buildTree("1,null,2,3")));
        System.out.println("[] == " + preorderTraversal(TreeNode.buildTree("")));
        System.out.println("[1] == " + preorderTraversal(TreeNode.buildTree("1")));

        System.out.println("[1, 2, 3] == " + preorderTraversal2(TreeNode.buildTree("1,null,2,3")));
        System.out.println("[] == " + preorderTraversal2(TreeNode.buildTree("")));
        System.out.println("[1] == " + preorderTraversal2(TreeNode.buildTree("1")));
    }

    private static List<Integer> result = null;

    public static List<Integer> preorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        traverse(root);
        return result;
    }

    private static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        result.addAll(preorderTraversal2(root.left));
        result.addAll(preorderTraversal2(root.right));

        return result;
    }

}
