package lintcode.bfs;

import java.util.*;

public class _127_Topological_Sorting {

  public static List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
    if (graph == null || graph.size() == 0) {
      return new ArrayList<>();
    }

    Map<DirectedGraphNode, Integer> inDegrees = new HashMap<>();
    for (DirectedGraphNode node : graph) {
      for (DirectedGraphNode neighbor : node.neighbors) {
        inDegrees.put(neighbor, inDegrees.getOrDefault(neighbor, 0) + 1);
      }
    }

    ArrayList<DirectedGraphNode> result = new ArrayList<>();
    Queue<DirectedGraphNode> queue = new LinkedList<>();
    for (DirectedGraphNode node : graph) {
      if (!inDegrees.containsKey(node)) {
        queue.offer(node);
        result.add(node);
      }
    }

    while (!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();

      for (DirectedGraphNode adj : node.neighbors) {
        inDegrees.put(adj, inDegrees.get(adj) - 1);

        if (inDegrees.get(adj) == 0) {
          queue.offer(adj);
          result.add(adj);
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println("[0, 1, 2, 3, 4, 5] == " + topSort(buildGraph("0,1,2,3#1,4#2,4,5#3,4,5")));
    System.out.println("[0, 2, 1, 3, 4] == " + topSort(buildGraph("0,1,2,3,4#1,3,4#2,1,4#3,4#4")));
  }

  private static List<DirectedGraphNode> buildGraph(String s) {
    Set<DirectedGraphNode> graph = new TreeSet<>(Comparator.comparingInt(a -> a.label));

    String[] groups = s.split("\\#");
    for (String group : groups) {
      String[] nodeStrs = group.split("\\,");

      Set<DirectedGraphNode> neighborSet = new HashSet<>();
      for (int i = 1; i < nodeStrs.length; i++) {
        DirectedGraphNode neighbor = DirectedGraphNode.create(Integer.valueOf(nodeStrs[i]));
        neighborSet.add(neighbor);
        graph.add(neighbor);
      }

      DirectedGraphNode source = DirectedGraphNode.create(Integer.valueOf(nodeStrs[0]));
      source.neighbors = new ArrayList<>(neighborSet);
      graph.add(source);
    }

    return new ArrayList<>(graph);
  }

  static class DirectedGraphNode {
    static Map<Integer, DirectedGraphNode> map = new HashMap<>();

    int label;
    List<DirectedGraphNode> neighbors;

    private DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }

    public static DirectedGraphNode create(int x) {
      if (map.containsKey(x)) {
        return map.get(x);
      }

      DirectedGraphNode node = new DirectedGraphNode(x);
      map.put(x, node);

      return node;
    }

    @Override
    public String toString() {
      return String.valueOf(label);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DirectedGraphNode that = (DirectedGraphNode) o;
      return label == that.label;
    }

    @Override
    public int hashCode() {
      return Objects.hash(label);
    }
  }
}
