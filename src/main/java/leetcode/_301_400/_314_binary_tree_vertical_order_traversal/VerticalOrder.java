package leetcode._301_400._314_binary_tree_vertical_order_traversal;

import java.util.*;

/**
 * 二叉树垂直遍历 中文English 给定二叉树，返回其节点值的垂直遍历顺序。 (即逐列从上到下)。 如果两个节点在同一行和同一列中，则顺序应 从左到右。
 *
 * <p>样例 样例1
 *
 * <p>输入： {3,9,20,#,#,15,7} 输出： [[9],[3,15],[20],[7]] 解释： 3 /\ / \ 9 20 /\ / \ 15 7 样例2
 *
 * <p>输入： {3,9,8,4,0,1,7} 输出：[[4],[9],[3,0,1],[8],[7]] 解释： 3 /\ / \ 9 8 /\ /\ / \/ \ 4 01 7
 */
public class VerticalOrder {

  public static void main(String[] args) {
    TreeNode root1 = deserializeTree("{3,9,20,#,#,15,7}");

    System.out.println("[[9], [3, 15], [20], [7]] == " + new VerticalOrder().verticalOrder(root1));

    TreeNode root2 = deserializeTree("{3,9,8,4,0,1,7}");

    System.out.println(
        "[[4], [9], [3, 0, 1], [8], [7]] == " + new VerticalOrder().verticalOrder(root2));
  }

  private static TreeNode deserializeTree(String s) {
    if (s == null || s.length() == 0) {
      return null;
    }

    s = s.substring(1, s.length() - 1);

    System.out.println(s);

    String[] strs = s.split(",");

    int l = strs.length;

    Queue<TreeNode> queue = new LinkedList<>();

    TreeNode root = new TreeNode(Integer.parseInt(strs[0]));

    queue.offer(root);

    int index = 0;

    while (!queue.isEmpty() && index < l) {

      TreeNode head = queue.poll();

      index++;

      if (index < l && !strs[index].equals("#")) {
        TreeNode left = new TreeNode(Integer.parseInt(strs[index]));

        head.left = left;

        queue.offer(left);
      }

      index++;

      if (index < l && !strs[index].equals("#")) {
        TreeNode right = new TreeNode(Integer.parseInt(strs[index]));

        head.right = right;

        queue.offer(right);
      }
    }

    return root;
  }

  /**
   * @param root: the root of tree
   * @return: the vertical order traversal
   */
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    Map<Integer, List<Integer>> map = new TreeMap<>();

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    Queue<Integer> indexQueue = new LinkedList<>();

    indexQueue.offer(0);

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        TreeNode head = queue.poll();

        int index = indexQueue.poll();

        map.computeIfAbsent(index, k -> new ArrayList<>());

        map.get(index).add(head.val);

        if (head.left != null) {
          queue.offer(head.left);

          indexQueue.offer(index - 1);
        }

        if (head.right != null) {
          queue.offer(head.right);

          indexQueue.offer(index + 1);
        }
      }
    }

    for (int key : map.keySet()) {
      result.add(map.get(key));
    }

    return result;
  }
}

class TreeNode {

  public int val;

  public TreeNode left, right;

  public TreeNode(int val) {
    this.val = val;

    this.left = this.right = null;
  }
}
