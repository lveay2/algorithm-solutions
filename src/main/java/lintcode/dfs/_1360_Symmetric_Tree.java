package lintcode.dfs;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1360_Symmetric_Tree {

    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }

        if (leftRoot == null || rightRoot == null) {
            return false;
        }

        if (leftRoot.val != rightRoot.val) {
            return false;
        }

        boolean left = isSymmetric(leftRoot.left, rightRoot.right);
        boolean right = isSymmetric(leftRoot.right, rightRoot.left);

        return left && right;
    }

    public static void main(String[] args) {
        System.out.println(isSymmetric(TreeNode.buildTree("1,2,2,3,4,4,3")));
        System.out.println(isSymmetric(TreeNode.buildTree("1,2,2,#,3,#,3")));
        System.out.println(isSymmetric2(TreeNode.buildTree("1,2,2,3,4,4,3")));
        System.out.println(isSymmetric2(TreeNode.buildTree("1,2,2,#,3,#,3")));
    }

}
