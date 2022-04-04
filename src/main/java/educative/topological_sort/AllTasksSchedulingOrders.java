package educative.topological_sort;

import java.util.*;

public class AllTasksSchedulingOrders {

  public static void printOrders(int tasks, int[][] prerequisites) {
    List<Integer> sortedOrder = new ArrayList<>();

    if (tasks == 0
        || prerequisites == null
        || prerequisites.length == 0
        || prerequisites[0].length == 0) {
      return;
    }

    Map<Integer, Integer> indegrees = new HashMap<>();
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int i = 0; i < tasks; i++) {
      indegrees.put(i, 0);
      graph.put(i, new HashSet<>());
    }

    for (int[] prerequisite : prerequisites) {
      int prer = prerequisite[0];
      int later = prerequisite[1];
      indegrees.put(later, indegrees.get(later) + 1);
      graph.get(prer).add(later);
    }

    Queue<Integer> queue = new LinkedList<>();
    for (Map.Entry<Integer, Integer> e : indegrees.entrySet()) {
      if (e.getValue() == 0) {
        queue.offer(e.getKey());
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      count++;
      sortedOrder.add(current);

      for (int neigbhor : graph.get(current)) {
        indegrees.put(neigbhor, indegrees.get(neigbhor) - 1);

        if (indegrees.get(neigbhor) == 0) {
          queue.offer(neigbhor);
        }
      }
    }

    if (count != tasks) {
      return;
    }
  }

  public static void main(String[] args) {
    AllTasksSchedulingOrders.printOrders(3, new int[][] {new int[] {0, 1}, new int[] {1, 2}});
    System.out.println();

    AllTasksSchedulingOrders.printOrders(
        4, new int[][] {new int[] {3, 2}, new int[] {3, 0}, new int[] {2, 0}, new int[] {2, 1}});
    System.out.println();

    AllTasksSchedulingOrders.printOrders(
        6,
        new int[][] {
          new int[] {2, 5},
          new int[] {0, 5},
          new int[] {0, 4},
          new int[] {1, 4},
          new int[] {3, 2},
          new int[] {1, 3}
        });
    System.out.println();
  }
}
