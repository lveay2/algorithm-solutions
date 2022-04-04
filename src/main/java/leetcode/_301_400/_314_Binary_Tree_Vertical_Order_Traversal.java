package leetcode._301_400;

import java.util.*;

public class _314_Binary_Tree_Vertical_Order_Traversal {

  public static List<List<Integer>> verticalOrder(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<List<Integer>> result = new ArrayList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    Map<TreeNode, Integer> n2c = new HashMap<>();
    n2c.put(root, 0);

    Map<Integer, List<Integer>> col2List = new TreeMap<>();

    int min = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      int col = n2c.get(node);
      min = Math.min(min, col);

      col2List.putIfAbsent(col, new ArrayList<>());
      col2List.get(col).add(node.val);

      if (node.left != null) {
        queue.offer(node.left);
        n2c.put(node.left, col - 1);
      }

      if (node.right != null) {
        queue.offer(node.right);
        n2c.put(node.right, col + 1);
      }
    }

    while (col2List.containsKey(min)) {
      result.add(col2List.get(min++));
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode _3 = new TreeNode(3);
    TreeNode _9 = new TreeNode(9);
    TreeNode _20 = new TreeNode(20);
    TreeNode _15 = new TreeNode(15);
    TreeNode _7 = new TreeNode(7);

    _3.left = _9;
    _3.right = _20;
    _20.left = _15;
    _20.right = _7;
    System.out.println(verticalOrder(_3));
  }
}

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
