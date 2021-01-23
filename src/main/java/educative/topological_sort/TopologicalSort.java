package educative.topological_sort;

import java.util.*;

public class TopologicalSort {

    public static List<Integer> sort(int vertices, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (vertices == 0 || edges == null || edges.length == 0 || edges[0].length == 0) {
            return result;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegress = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            graph.put(i, new HashSet<>());
            indegress.put(i, 0);
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegress.put(to, indegress.get(to) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> e : indegress.entrySet()) {
            if (e.getValue() == 0) {
                queue.offer(e.getKey());
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            result.add(current);

            for (int neighbor : graph.get(current)) {
                indegress.put(neighbor, indegress.get(neighbor) - 1);

                if (indegress.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (count != vertices) {
            return new ArrayList<>();
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][]{new int[]{3, 2}, new int[]{3, 0}, new int[]{2, 0}, new int[]{2, 1}});
        System.out.println("[3, 2, 0, 1] == " + result);

        result = TopologicalSort.sort(5, new int[][]{new int[]{4, 2}, new int[]{4, 3}, new int[]{2, 0},
                new int[]{2, 1}, new int[]{3, 1}});
        System.out.println("[4, 2, 3, 0, 1] == " + result);

        result = TopologicalSort.sort(7, new int[][]{new int[]{6, 4}, new int[]{6, 2}, new int[]{5, 3},
                new int[]{5, 4}, new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{4, 1}});
        System.out.println("[5, 6, 3, 4, 0, 2, 1] == " + result);
    }

}
