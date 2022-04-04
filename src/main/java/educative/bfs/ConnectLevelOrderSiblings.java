package educative.bfs;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each
 * level should point to a null node.
 */
public class ConnectLevelOrderSiblings {

  public static void connect(TreeNode root) {
    if (root == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode previousNode = null;
      int levelSize = queue.size();
      // connect all nodes of this level
      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        if (previousNode != null) {
          previousNode.next = currentNode;
        }
        previousNode = currentNode;

        // insert the children of current node in the queue
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
    }
  }

  public static void main(String[] args) {
    TreeNode root = TreeNode.buildTree("12,7,1,9,null,10,5");
    ConnectLevelOrderSiblings.connect(root);
    System.out.println("Level order traversal using 'next' pointer: ");
    root.printLevelOrder();
  }
}
