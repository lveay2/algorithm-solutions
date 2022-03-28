package leetcode._201_300;

import common.TreeNode;

/*
230. Kth Smallest Element in a BST
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104
 */
public class _230_KthSmallestElementInABST {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("5,3,6,2,4,null,null,1");
        System.out.println("3 == " + kthSmallest(root, 3));
    }

    static int rank = 0, ans = 0;
    public static int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }

    private static void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        dfs(root.left, k);
        rank++;
        if (rank == k) {
            ans = root.val;
            return;
        }
        dfs(root.right, k);
    }

}
