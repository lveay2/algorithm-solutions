package educative.dfs;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all
 * the node values of each path equals ‘S’.
 */
public class FindAllTreePaths {

    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        int s = 1;
        System.out.println("1 " + s);
        findPathsRecursive(root, sum, currentPath, allPaths, s);
        System.out.println("2 " + s);
        return allPaths;
    }

    private static void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath,
                                           List<List<Integer>> allPaths, int s) {
        if (currentNode == null) {
            return;
        }
        System.out.println("3 " + s);
        s = 2;
        System.out.println("4 " + s);
        // add the current node to the path
        currentPath.add(currentNode.val);

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // traverse the left sub-tree
            findPathsRecursive(currentNode.left, sum - currentNode.val, currentPath, allPaths, s);
            // traverse the right sub-tree
            findPathsRecursive(currentNode.right, sum - currentNode.val, currentPath, allPaths, s);
        }

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum 23 == " + sum + ": [[12, 7, 4], [12, 1, 10]] == " + result);
    }

}
