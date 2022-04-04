package lintcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _262_heir_tree {

  Map<Integer, MyTreeNode> map = new HashMap<>();

  public _262_heir_tree() {
    this.map.put(1, new MyTreeNode(1));
  }

  public static void main(String[] args) {
    _262_heir_tree solution = new _262_heir_tree();
    solution.add(1, 2);
    solution.add(2, 3);
    System.out.println(solution.traverse(1));
    solution.add(2, 5);
    solution.add(3, 4);
    System.out.println(solution.traverse(1));
    solution.deleteNode(2);
    System.out.println(solution.traverse(1));
  }

  /**
   * @param value: the value of root treenode
   * @return: get the traverse of the treenode
   */
  public ArrayList<Integer> traverse(int value) {
    return traverse(map.get(value));
  }

  public ArrayList<Integer> traverse(MyTreeNode root) {
    ArrayList<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    if (root.valid) {
      result.add(root.val);
    }

    for (MyTreeNode child : root.children) {
      List<Integer> coc = traverse(child);
      result.addAll(coc);
    }

    return result;
  }

  /**
   * @param root: the node where added
   * @param value: the added node's value
   * @return: add a node, return the node
   */
  public MyTreeNode add(MyTreeNode root, int value) {
    if (!map.containsKey(value)) {
      map.put(value, new MyTreeNode(value));
    }

    MyTreeNode node = map.get(value);
    root.children.add(node);

    return node;
  }

  public MyTreeNode add(int root, int value) {
    return add(map.get(root), value);
  }

  /**
   * @param root: the deleted node
   * @return: nothing
   */
  public void deleteNode(MyTreeNode root) {
    root.valid = false;
  }

  public void deleteNode(int value) {
    deleteNode(map.get(value));
  }

  static class MyTreeNode {

    int val;
    List<MyTreeNode> children = new ArrayList<>();
    boolean valid;

    /**
     * @param val: the val of the node
     * @return: a MyTreeNode Object
     */
    MyTreeNode(int val) {
      this.val = val;
      this.valid = true;
    }
  }
}
