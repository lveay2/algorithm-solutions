package leetcode._201_300._261_graph_valid_tree;

import java.util.*;

/**
 * Description Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a
 * pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * <p>You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * <p>Have you met this question in a real interview?
 *
 * <p>Example Example 1: Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]] Output: true.
 *
 * <p>Example 2: Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]] Output: false.
 */
public class ValidTree {

  public static void main(String[] args) {
    int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};

    System.out.println("true == " + new ValidTree().validTree(5, edges));

    int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {1, 3}};

    System.out.println("false == " + new ValidTree().validTree(5, edges3));

    int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};

    System.out.println("false == " + new ValidTree().validTree(5, edges2));
  }

  /**
   * @param n: An integer
   * @param edges: a list of undirected edges
   * @return: true if it's a valid tree, or false
   */
  public boolean validTree(int n, int[][] edges) {
    if (n == 0 || edges.length == 0) return false;

    if (edges.length != n - 1) {
      return false;
    }

    HashMap<Integer, HashSet<Integer>> graph = initializeGraph(n, edges);

    Queue<Integer> queue = new LinkedList<>();

    Set<Integer> set = new HashSet<>();

    queue.add(0);

    set.add(0);

    while (!queue.isEmpty()) {

      int node = queue.poll();

      for (Integer neighbour : graph.get(node)) {
        if (set.contains(neighbour)) {
          continue;
        }

        set.add(neighbour);

        queue.offer(neighbour);
      }
    }

    return (set.size() == n);
  }

  private HashMap<Integer, HashSet<Integer>> initializeGraph(int n, int[][] edges) {
    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    for (int i = 0; i < n; i++) {
      graph.computeIfAbsent(i, k -> new HashSet<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];

      int v = edges[i][1];

      graph.get(u).add(v);

      graph.get(v).add(u);
    }

    return graph;
  }
}
