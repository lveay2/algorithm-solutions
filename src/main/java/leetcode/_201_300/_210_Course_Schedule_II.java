package leetcode._201_300;

import java.util.*;

public class _210_Course_Schedule_II {

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] result = new int[numCourses];
    int[] inDegrees = new int[numCourses];
    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int[] pre : prerequisites) {
      int dest = pre[0];
      int src = pre[1];
      if (!map.containsKey(src)) {
        map.put(src, new ArrayList<>());
      }
      map.get(src).add(dest);
      inDegrees[dest]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegrees[i] == 0) {
        queue.add(i);
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result[count++] = current;

      if (map.containsKey(current)) {
        for (int n : map.get(current)) {
          inDegrees[n]--;

          if (inDegrees[n] == 0) {
            queue.add(n);
          }
        }
      }
    }

    return count == numCourses ? result : new int[0];
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(findOrder(2, new int[][] {{1, 0}})));
    System.out.println(Arrays.toString(findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
  }
}
