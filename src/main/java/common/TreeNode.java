package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;
  public TreeNode next;
  public TreeNode parent;
  List<Integer> values = new ArrayList<>();

  public TreeNode(int x) {
    val = x;
  }

  public static TreeNode buildTree(String str) {
    if (str == null || str.length() == 0) {
      return null;
    }

    String[] values = str.split(",");

    TreeNode root = new TreeNode(Integer.parseInt(values[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int i = 0, l = values.length;

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();

      i++;
      if (i < l && !values[i].equals("null") && !values[i].equals("#")) {
        TreeNode leftNode = new TreeNode(Integer.parseInt(values[i]));
        leftNode.parent = node;
        queue.offer(leftNode);
        node.left = leftNode;
      }

      i++;
      if (i < l && !values[i].equals("null") && !values[i].equals("#")) {
        TreeNode rightNode = new TreeNode(Integer.parseInt(values[i]));
        rightNode.parent = node;
        queue.offer(rightNode);
        node.right = rightNode;
      }
    }

    return root;
  }

  static TreeNode found = null;
  public static TreeNode getNode(TreeNode root, int val) {
    found = null;
    getNodeDfs(root, val);
    return found;
  }

  private static void getNodeDfs(TreeNode root, int val) {
    if (root == null) {
      return;
    }
    if (root.val == val) {
      found = root;
      return;
    }
    if (found != null) {
      return;
    }

    getNodeDfs(root.left, val);
    getNodeDfs(root.right, val);
  }


  // level order traversal using 'next' pointer
  public void printLevelOrder() {
    TreeNode nextLevelRoot = this;
    while (nextLevelRoot != null) {
      TreeNode current = nextLevelRoot;
      nextLevelRoot = null;
      while (current != null) {
        System.out.print(current.val + " ");
        if (nextLevelRoot == null) {
          if (current.left != null) {
            nextLevelRoot = current.left;
          } else if (current.right != null) {
            nextLevelRoot = current.right;
          }
        }
        current = current.next;
      }
      System.out.println();
    }
  }

  @Override
  public String toString() {
    printLevelOrder();
    return "";
  }

  public String printTreeInPreorder() {
    dfs(this);
    return values.toString();
  }

  private void dfs(TreeNode root) {
    if (root == null) {
      return;
    }
    values.add(root.val);
    dfs(root.left);
    dfs(root.right);
  }
}
