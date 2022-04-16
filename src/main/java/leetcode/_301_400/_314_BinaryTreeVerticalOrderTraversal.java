package leetcode._301_400;

import common.TreeNode;

import java.util.*;

/*
314. Binary Tree Vertical Order Traversal
Given the root of a binary tree, return the vertical order traversal of
its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from
left to right.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Example 2:
Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class _314_BinaryTreeVerticalOrderTraversal {

  public static void main(String[] args) {
    System.out.println("[[2], [1], [3]] ==\n" + verticalOrder(TreeNode.buildTree("1,2,3")));
    System.out.println(
        "[[9], [3, 15], [20], [7]] ==\n"
            + verticalOrder(TreeNode.buildTree("3,9,20,null,null,15,7")));
    System.out.println(
        "[[4], [9], [3, 0, 1], [8], [7]] ==\n"
            + verticalOrder(TreeNode.buildTree("3,9,8,4,0,1,7")));
    System.out.println(
        "[[4], [9, 5], [3, 0, 1], [8, 2], [7]] ==\n"
            + verticalOrder(TreeNode.buildTree("3,9,8,4,0,1,7,null,null,null,2,5")));
    System.out.println("[] == " + verticalOrder(null));
  }

  public static List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    Map<TreeNode, Integer> node2Col = new HashMap<>();
    node2Col.put(root, 0);

    Map<Integer, List<Integer>> col2List = new HashMap<>();
    int min = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      int n = queue.size();

      for (int i = 0; i < n; i++) {
        TreeNode node = queue.poll();

        int col = node2Col.get(node);
        col2List.putIfAbsent(col, new ArrayList<>());
        col2List.get(col).add(node.val);
        min = Math.min(min, col);

        if (node.left != null) {
          queue.offer(node.left);
          node2Col.put(node.left, col - 1);
        }
        if (node.right != null) {
          queue.offer(node.right);
          node2Col.put(node.right, col + 1);
        }
      }
    }

    while (col2List.containsKey(min)) {
      result.add(col2List.get(min++));
    }

    return result;
  }
}
