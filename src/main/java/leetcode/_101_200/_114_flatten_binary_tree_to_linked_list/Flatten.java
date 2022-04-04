package leetcode._101_200._114_flatten_binary_tree_to_linked_list;

import common.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * <p>For example, given the following tree:
 *
 * <p>1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like:
 *
 * <p>1 \ 2 \ 3 \ 4 \ 5 \ 6
 */
public class Flatten {

  public static void main(String[] args) {
    TreeNode one = new TreeNode(1);

    TreeNode two = new TreeNode(2);

    TreeNode three = new TreeNode(3);

    TreeNode four = new TreeNode(4);

    TreeNode five = new TreeNode(5);

    TreeNode six = new TreeNode(6);

    one.left = two;

    one.right = five;

    two.left = three;

    two.right = four;

    five.right = six;

    System.out.println("before: " + print(one));

    new Flatten().flatten(one);

    System.out.println("after: " + print(one));
  }

  private static String print(TreeNode root) {
    StringBuilder sb = new StringBuilder(Integer.toString(root.val));

    while (root.right != null) {
      sb.append("->" + root.right.val);

      root = root.right;
    }

    return sb.toString();
  }

  public void flatten(TreeNode root) {
    helper(root);
  }

  private TreeNode helper(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode leftLast = helper(root.left);

    TreeNode rightLast = helper(root.right);

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
}
