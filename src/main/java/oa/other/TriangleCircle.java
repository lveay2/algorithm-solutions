package oa.other;


import java.util.*;

public class TriangleCircle {

  public static void main(String[] args) {
    List<Circle> circles = new ArrayList<>();
    circles.add(new Circle(0,0,1));
    circles.add(new Circle(0,1,1));
    circles.add(new Circle(2,1,1));
    System.out.println(findPath(circles, 3, 3));
  }

  private static boolean findPath(List<Circle> circles, int width, int height) {
    Map<Integer, List<Integer>> graph = buildGraph(circles);
    System.out.println(graph);

    int n = circles.size();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }

      int xmin = Integer.MAX_VALUE,
          ymin = Integer.MAX_VALUE,
          xmax = Integer.MIN_VALUE,
          ymax = Integer.MIN_VALUE;
      //      Set<Integer> set = new HashSet<>();
      Queue<Integer> queue = new LinkedList<>();
      queue.offer(i);
      while (!queue.isEmpty()) {
        int current = queue.poll();
        if (visited[i]) {
          continue;
        }
        Circle c = circles.get(current);

        xmin = Math.min(xmin, c.x - c.r);
        ymin = Math.min(ymin, c.y - c.r);
        xmax = Math.max(xmax, c.x + c.r);
        ymax = Math.max(ymax, c.y + c.r);
        if (isBlock(xmin, ymin, xmax, ymax, width, height)) {
          return false;
        }

        for (int adj : graph.get(current)) {
          //          set.add(adj);
          visited[adj] = true;
          queue.add(adj);
        }
      }
    }

    return true;
  }

  private static boolean isBlock(int xmin, int ymin, int xmax, int ymax, int width, int height) {
    if (xmin <= 0 && xmax >= width) {
      return false;
    }
    if (ymin <= 0 && ymax >= height) {
      return false;
    }
    if (xmin <= 0 && ymin <= 0) {
      return false;
    }
    if (xmax >= width && ymax >= height) {
      return false;
    }
    return true;
  }

  private static Map<Integer, List<Integer>> buildGraph(List<Circle> circles) {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    int n = circles.size();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          continue;
        }

        if (isConnected(circles.get(i), circles.get(j))) {
          graph.putIfAbsent(i, new ArrayList<>());
          graph.get(i).add(j);
        }
      }
    }

    return graph;
  }

  private static boolean isConnected(Circle c1, Circle c2) {
    return (c1.r + c2.r) * (c1.r + c2.r) >= (c1.x - c2.x)*(c1.x - c2.x) + (c1.y - c2.y)*(c1.y - c2.y);
  }

  static class Circle {
    int x;
    int y;
    int r;
    public Circle(int x, int y, int r) {
      this.x = x;
      this.y = y;
      this.r = r;
    }
  }
}
