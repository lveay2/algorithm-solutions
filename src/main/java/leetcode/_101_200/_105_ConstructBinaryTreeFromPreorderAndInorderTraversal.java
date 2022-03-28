package leetcode._101_200;

import java.util.ArrayList;
import java.util.List;

/*
105. Construct Binary Tree from Preorder and Inorder Traversal
Given two integer arrays preorder and inorder where preorder is the
preorder traversal of a binary tree and inorder is the inorder
traversal of the same tree, construct and return the binary tree.


Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]

Constraints:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length,
                inorder, 0, inorder.length);
    }

    private static TreeNode helper(int[] preorder, int preStart, int preEnd,
                                   int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;

        root.left = helper(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);
        root.right = helper(preorder, preEnd + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        List<Integer> values = new ArrayList<>();

        @Override
        public String toString() {
            dfs(this);
            return values.toString();
        }

        private void dfs(TreeNode root) {
            if (root == null) {
                return;
            }
            values.add(root.val);
            dfs(root.left);
            dfs(root.right);
        }
    }
}

