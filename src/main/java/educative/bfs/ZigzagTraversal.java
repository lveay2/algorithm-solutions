package educative.bfs;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent its zigzag level order traversal. You should
 * populate the values of all nodes of the first level from left to right, then right to left for
 * the next level and keep alternating in the same manner for the following levels.
 */
public class ZigzagTraversal {

  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentLevel = new LinkedList<>();
      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();

        // add the node to the current level based on the traverse direction
        if (leftToRight) {
          currentLevel.add(currentNode.val);
        } else {
          currentLevel.add(0, currentNode.val);
        }

        // insert the children of current node in the queue
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
      result.add(currentLevel);
      // reverse the traversal direction
      leftToRight = !leftToRight;
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.right.left.left = new TreeNode(20);
    root.right.left.right = new TreeNode(17);
    List<List<Integer>> result = ZigzagTraversal.traverse(root);
    System.out.println("Zigzag traversal: [12], [1, 7], [9, 10, 5], [17, 20]] == " + result);
  }
}
