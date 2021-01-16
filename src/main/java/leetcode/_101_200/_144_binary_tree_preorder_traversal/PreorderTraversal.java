package leetcode._101_200._144_binary_tree_preorder_traversal;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * <p>Example:
 *
 * <p>Input: [1,null,2,3] 1 \ 2 / 3
 *
 * <p>Output: [1,2,3]
 */
public class PreorderTraversal {

    public static void main(String[] args) {
        TreeNode one = TreeNode.buildTree("1,null,2,3");
        System.out.println("[1, 2, 3] == " + new PreorderTraversal().preorderTraversal(one));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();

        if (root == null) {
            return r;
        }

        List<Integer> left = preorderTraversal(root.left);

        List<Integer> right = preorderTraversal(root.right);

        r.add(root.val);

        r.addAll(left);

        r.addAll(right);

        return r;
    }

}
