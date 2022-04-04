package leetcode._201_300._257_binary_tree_paths;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * <p>Note: A leaf is a node with no children.
 *
 * <p>Example:
 *
 * <p>Input:
 *
 * <p>1 / \ 2 3 \ 5
 *
 * <p>Output: ["1->2->5", "1->3"]
 *
 * <p>Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {

  private static TreeNode deserialize(String tree) {
    if (tree == null || tree.length() == 0) {
      return null;
    }

    String[] trees = tree.split(",");

    Queue<TreeNode> queue = new LinkedList<>();

    TreeNode root = new TreeNode(Integer.parseInt(trees[0]));

    queue.offer(root);

    int l = trees.length;

    int i = 0;

    while (!queue.isEmpty() && i < l) {
      TreeNode head = queue.poll();

      i++;

      if (i < l && !trees[i].equals("null")) {
        TreeNode left = new TreeNode(Integer.parseInt(trees[i]));

        head.left = left;

        queue.offer(left);
      }

      i++;

      if (i < l && !trees[i].equals("null")) {
        TreeNode right = new TreeNode(Integer.parseInt(trees[i]));

        head.right = right;

        queue.offer(right);
      }
    }

    return root;
  }

  public static void main(String[] args) {
    System.out.println(
        "[1->2->5, 1->3] == " + new BinaryTreePaths().binaryTreePaths(deserialize("1,2,3,null,5")));

    System.out.println(
        "[1->2->4, 1->2->5, 1->3] == "
            + new BinaryTreePaths().binaryTreePaths(deserialize("1,2,3,4,5")));
  }

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> r = new ArrayList<>();

    if (root == null) {
      return r;
    }

    if (root.left == null && root.right == null) {
      r.add(Integer.toString(root.val));

      return r;
    }

    List<String> leftPath = binaryTreePaths(root.left);

    List<String> rightPath = binaryTreePaths(root.right);

    for (String path : leftPath) {
      r.add(root.val + "->" + path);
    }

    for (String path : rightPath) {
      r.add(root.val + "->" + path);
    }

    return r;
  }
}
