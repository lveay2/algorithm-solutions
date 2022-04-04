package lintcode.dfs;

import common.TreeNode;

public class _453_Flatten_Binary_Tree_to_Linked_List {

  private static TreeNode last = null;

  public static void flatten2(TreeNode root) {
    if (root == null) {
      return;
    }

    if (last != null) {
      last.left = null;
      last.right = root;
    }

    last = root;
    TreeNode right = root.right;
    flatten2(root.left);
    flatten2(right);
  }

  public static void flatten(TreeNode root) {
    dfs(root);
  }

  private static TreeNode dfs(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode leftLast = dfs(root.left);
    TreeNode rightLast = dfs(root.right);

    if (leftLast != null) {
      leftLast.right = root.right;
      root.right = root.left;
      root.left = null;
    }

    if (rightLast != null) {
      return rightLast;
    }

    if (leftLast != null) {
      return leftLast;
    }

    return root;
  }

  public static void main(String[] args) {
    TreeNode root = TreeNode.buildTree("1,2,5,3,4,#,6");
    flatten(root);
    while (root != null) {
      System.out.print(root.val);
      root = root.right;
      if (root != null) {
        System.out.print(" -> ");
      }
    }
    System.out.println();

    root = TreeNode.buildTree("1,2,5,3,4,#,6");
    flatten2(root);
    while (root != null) {
      System.out.print(root.val);
      root = root.right;
      if (root != null) {
        System.out.print(" -> ");
      }
    }
  }
}
