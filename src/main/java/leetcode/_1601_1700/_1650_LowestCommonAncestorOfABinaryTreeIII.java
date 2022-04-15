package leetcode._1601_1700;

import common.TreeNode;

/*
1650. Lowest Common Ancestor of a Binary Tree III
Given two nodes of a binary tree p and q, return their lowest common
ancestor (LCA).

Each node will have a reference to its parent node. The definition for
Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common
ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1
 */
public class _1650_LowestCommonAncestorOfABinaryTreeIII {

  public static void main(String[] args) {
    TreeNode root = TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");
    TreeNode p = TreeNode.getNode(root, 5);
    TreeNode q = TreeNode.getNode(root, 4);
    System.out.println("5 == " + lowestCommonAncestor(p, q).val);

    p = TreeNode.getNode(root, 5);
    q = TreeNode.getNode(root, 1);
    System.out.println("3 == " + lowestCommonAncestor(p, q).val);

    root = TreeNode.buildTree("1,2");
    p = TreeNode.getNode(root, 1);
    q = TreeNode.getNode(root, 2);
    System.out.println("1 == " + lowestCommonAncestor(p, q).val);
  }

  public static TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
    TreeNode a = p, b = q;
    while (a != b) {
      if (a != null) {
        a = a.parent;
      } else {
        a = q;
      }

      if (b != null) {
        b = b.parent;
      } else {
        b = p;
      }
    }

    return a;
  }
}
