package leetcode._201_300;

import java.util.*;

/*
207. Course Schedule
There are a total of numCourses courses you have to take,
labeled from 0 to numCourses - 1. You are given an array
prerequisites where prerequisites[i] = [ai, bi] indicates
that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take
course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise,
return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Constraints:
1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class _207_CourseSchedule {

  static boolean[] visited;
  static boolean[] onPath;
  static boolean hasCycle = false;

  public static void main(String[] args) {
    System.out.println("canFinish");
    System.out.println("true == " + canFinish(2, new int[][] {{1, 0}}));
    System.out.println("false == " + canFinish(2, new int[][] {{1, 0}, {0, 1}}));
    System.out.println("true == " + canFinish(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    System.out.println(
        "false == "
            + canFinish(
                20,
                new int[][] {
                  {0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}
                }));
    System.out.println("false == " + canFinish(2, new int[][] {{1, 1}}));

    System.out.println("\ncanFinishBFS");
    System.out.println("true == " + canFinishBFS(2, new int[][] {{1, 0}}));
    System.out.println("false == " + canFinishBFS(2, new int[][] {{1, 0}, {0, 1}}));
    System.out.println("true == " + canFinishBFS(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
  }

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    List[] graph = buildGraph(numCourses, prerequisites);

    visited = new boolean[numCourses];
    onPath = new boolean[numCourses];
    hasCycle = false;

    for (int i = 0; i < numCourses; i++) {
      traverse(i, graph);
    }

    return !hasCycle;
  }

  private static void traverse(int i, List<Integer>[] graph) {
    if (onPath[i]) {
      hasCycle = true;
      return;
    }

    if (visited[i]) {
      return;
    }

    visited[i] = true;
    onPath[i] = true;
    for (int j : graph[i]) {
      traverse(j, graph);
    }
    onPath[i] = false;
  }

  private static List[] buildGraph(int numCourses, int[][] prerequisites) {
    List[] graph = new List[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph[i] = new LinkedList<Integer>();
    }

    for (int[] pre : prerequisites) {
      int from = pre[1], to = pre[0];
      graph[from].add(to);
    }

    return graph;
  }

  public static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
    int[] inDegrees = new int[numCourses];
    Map<Integer, Set<Integer>> map = new HashMap<>();

    for (int[] prerequisite : prerequisites) {
      int dest = prerequisite[0];
      int src = prerequisite[1];
      if (!map.containsKey(src)) {
        map.put(src, new HashSet<>());
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
      count++;

      if (map.containsKey(current)) {
        for (int adj : map.get(current)) {
          inDegrees[adj]--;

          if (inDegrees[adj] == 0) {
            queue.add(adj);
          }
        }
      }
    }

    return count == numCourses;
  }
}
