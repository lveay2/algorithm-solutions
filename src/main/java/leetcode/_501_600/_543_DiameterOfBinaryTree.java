package leetcode._501_600;

import common.TreeNode;

/*
543. Diameter of Binary Tree
Given the root of a binary tree, return the length of the
diameter of the tree.

The diameter of a binary tree is the length of the longest
path between any two nodes in a tree. This path may or may
not pass through the root.

The length of a path between two nodes is represented by
the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1

Constraints:
The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
 */
public class _543_DiameterOfBinaryTree {

  static int max = 0;

  public static void main(String[] args) {
    System.out.println("3 == " + diameterOfBinaryTree(TreeNode.buildTree("1,2,3,4,5")));
    System.out.println("1 == " + diameterOfBinaryTree(TreeNode.buildTree("1,2")));
  }

  public static int diameterOfBinaryTree(TreeNode root) {
    max = 0;
    maxDepth(root);
    return max;
  }

  private static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    max = Math.max(max, left + right);

    return 1 + Math.max(left, right);
  }
}
