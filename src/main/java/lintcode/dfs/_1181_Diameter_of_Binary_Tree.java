package lintcode.dfs;

import common.TreeNode;

public class _1181_Diameter_of_Binary_Tree {

    static int max = Integer.MIN_VALUE;

    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);

        return max;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        System.out.println(diameterOfBinaryTree(TreeNode.buildTree("1,2,3,4,5")));
    }

}
