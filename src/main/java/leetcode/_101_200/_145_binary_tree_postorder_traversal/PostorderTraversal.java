package leetcode._101_200._145_binary_tree_postorder_traversal;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * <p>Example:
 *
 * <p>Input: [1,null,2,3] 1 \ 2 / 3
 *
 * <p>Output: [3,2,1]
 */
public class PostorderTraversal {

  public static void main(String[] args) {
    TreeNode one = TreeNode.buildTree("1,2,null,3");
    System.out.println("[3, 2, 1] == " + new PostorderTraversal().postorderTraversal(one));
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> r = new ArrayList<>();

    if (root == null) {
      return r;
    }

    List<Integer> left = postorderTraversal(root.left);

    List<Integer> right = postorderTraversal(root.right);

    r.addAll(left);

    r.addAll(right);

    r.add(root.val);

    return r;
  }
}
