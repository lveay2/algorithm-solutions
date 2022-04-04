package leetcode._101_200;

import common.TreeNode;

/*
104. Maximum Depth of Binary Tree
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the
longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:
The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
 */
public class _104_MaximumDepthOfBinaryTree {

  public static void main(String[] args) {
    System.out.println("3 == " + maxDepth(TreeNode.buildTree("3,9,20,null,null,15,7")));
    System.out.println("2 == " + maxDepth(TreeNode.buildTree("1,null,2")));
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return Math.max(left + 1, right + 1);
  }
}
