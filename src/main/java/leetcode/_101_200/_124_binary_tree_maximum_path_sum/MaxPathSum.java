package leetcode._101_200._124_binary_tree_maximum_path_sum;

import common.TreeNode;

public class MaxPathSum {

    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode testCase0 = TreeNode.buildTree("2,-1");
        System.out.println("2 == " + new MaxPathSum().maxPathSum(testCase0));

        TreeNode testCase1 = TreeNode.buildTree("1,2,3");
        System.out.println("6 == " + new MaxPathSum().maxPathSum(testCase1));

        TreeNode testCase2 = TreeNode.buildTree("-10,9,20,null,null,15,7");
        System.out.println("42 == " + new MaxPathSum().maxPathSum(testCase2));

        TreeNode testCase3 = TreeNode.buildTree("5,4,8,11,null,13,4,7,2,null,null,null,1");
        System.out.println("48 == " + new MaxPathSum().maxPathSum(testCase3));

        TreeNode testCase4 = TreeNode.buildTree("1");
        System.out.println("1 == " + new MaxPathSum().maxPathSum(testCase4));
    }

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }

}
