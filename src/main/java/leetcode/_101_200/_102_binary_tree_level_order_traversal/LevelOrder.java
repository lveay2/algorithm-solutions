package leetcode._101_200._102_binary_tree_level_order_traversal;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to
 * right, level by level).
 *
 * <p>For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7 return its level
 * order traversal as: [ [3], [9,20], [15,7] ]
 */
public class LevelOrder {

  public static void main(String[] args) {
    TreeNode one = new TreeNode(3);
    TreeNode two = new TreeNode(9);
    TreeNode three = new TreeNode(20);
    TreeNode four = new TreeNode(15);
    TreeNode five = new TreeNode(7);

    three.left = four;
    three.right = five;

    one.left = two;
    one.right = three;

    TreeNode root = one;

    System.out.println(new LevelOrder().levelOrder(root));
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();

    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
      List<Integer> currentLevel = new ArrayList<>();

      int size = queue.size();

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        currentLevel.add(node.val);

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      result.add(currentLevel);
    }

    return result;
  }
}
