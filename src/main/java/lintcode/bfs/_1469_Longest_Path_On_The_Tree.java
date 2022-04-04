package lintcode.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class _1469_Longest_Path_On_The_Tree {

  public static int longestPath(int n, int[] starts, int[] ends, int[] lens) {
    if (starts == null
        || starts.length == 0
        || ends == null
        || ends.length == 0
        || lens == null
        || lens.length == 0) {
      return 0;
    }

    Map<Integer, Map<Integer, Integer>> graph = buildGraph(starts, ends, lens);
    int start = bfsHelper(0, graph)[0];
    return bfsHelper(start, graph)[1];
  }

  private static int[] bfsHelper(int start, Map<Integer, Map<Integer, Integer>> graph) {
    Map<Integer, Integer> distance = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    distance.put(start, 0);
    queue.offer(start);

    int max = 0, end = start;
    while (!queue.isEmpty()) {
      Integer current = queue.poll();
      int dist = distance.get(current);

      for (int next : graph.get(current).keySet()) {
        if (distance.containsKey(next)) {
          continue;
        }

        queue.offer(next);
        distance.put(next, dist + graph.get(current).get(next));
        if (distance.get(next) > max) {
          max = distance.get(next);
          end = next;
        }
      }
    }

    return new int[] {end, max};
  }

  private static Map<Integer, Map<Integer, Integer>> buildGraph(
      int[] starts, int[] ends, int[] lens) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

    for (int i = 0; i < starts.length; i++) {
      int start = starts[i];
      int end = ends[i];

      if (!graph.containsKey(start)) {
        graph.put(start, new HashMap<>());
      }
      if (!graph.containsKey(end)) {
        graph.put(end, new HashMap<>());
      }

      graph.get(start).put(end, lens[i]);
      graph.get(end).put(start, lens[i]);
    }

    return graph;
  }

  public static void main(String[] args) {
    System.out.println(
        longestPath(5, new int[] {0, 0, 2, 2}, new int[] {1, 2, 3, 4}, new int[] {1, 2, 5, 6}));
    System.out.println(
        longestPath(5, new int[] {0, 0, 2, 2}, new int[] {1, 2, 3, 4}, new int[] {5, 2, 5, 6}));
    System.out.println(longestPath(1, new int[] {}, new int[] {}, new int[] {}));
  }
}
