package lintcode._100_good_problems;

import common.TreeNode;

public class _94_Binary_Tree_Maximum_Path_Sum {

    private static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);

        return max;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        int currentMax = Math.max(root.val, Math.max(left + root.val, right + root.val));
        max = Math.max(max, Math.max(currentMax, left + right + root.val));
        return currentMax;
    }

    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.buildTree("2")));
        System.out.println(maxPathSum(TreeNode.buildTree("1,2,3")));
    }

}
