package lintcode.dfs;

import java.util.*;

public class _615_Course_Schedule {

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] degrees = new int[numCourses];

    for (int[] pre : prerequisites) {
      int dist = pre[0];
      int src = pre[1];

      graph.putIfAbsent(src, new ArrayList<>());
      graph.get(src).add(dist);
      degrees[dist]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (degrees[i] == 0) {
        queue.offer(i);
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int course = queue.poll();
      count++;

      if (graph.containsKey(course)) {
        for (int n : graph.get(course)) {
          degrees[n]--;

          if (degrees[n] == 0) {
            queue.offer(n);
          }
        }
      }
    }

    return count == numCourses;
  }

  public static void main(String[] args) {
    System.out.println(canFinish(2, new int[][] {{1, 0}}));
    System.out.println(canFinish(2, new int[][] {{1, 0}, {0, 1}}));
  }
}
