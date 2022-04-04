package lintcode.bfs;

import java.util.*;

public class _261_Maximum_Connected_Area {

  private static final Map<Point, Point> p2p = new HashMap<>();
  private static final Map<Point, Integer> p2Area = new HashMap<>();

  private static final int[] dx = {0, 0, -1, 1};
  private static final int[] dy = {1, -1, 0, 0};

  public static int maxArea(int[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;

    boolean[][] visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!visited[i][j] && matrix[i][j] == 1) {
          int area = bfs(matrix, i, j, n, m, visited);
          p2Area.put(new Point(i, j), area);
        }
      }
    }

    for (boolean[] arr : visited) {
      Arrays.fill(arr, false);
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == 0) {
          int area = 0;
          Set<Point> set = new HashSet<>();
          for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];

            Point origin = p2p.get(new Point(newX, newY));
            if (!set.contains(origin)) {
              set.add(origin);
              area += p2Area.get(origin);
              max = Math.max(max, area);
            }
          }
        }
      }
    }

    return max;
  }

  private static int bfs(int[][] matrix, int x, int y, int n, int m, boolean[][] visited) {
    int area = 1;

    Queue<Point> queue = new LinkedList<>();
    Point p = new Point(x, y);
    queue.offer(p);

    while (!queue.isEmpty()) {
      Point cp = queue.poll();
      area++;
      if (p != cp) {
        p2p.put(cp, p);
      }

      for (int i = 0; i < 4; i++) {
        int newX = cp.x + dx[i];
        int newY = cp.y + dy[i];

        if (0 <= newX
            && newX < n
            && 0 <= newY
            && newY < m
            && !visited[newX][newY]
            && matrix[newX][newY] == 1) {
          visited[newX][newY] = true;
          queue.offer(new Point(newX, newY));
        }
      }
    }

    return area;
  }

  public static void main(String[] args) {
    System.out.println(
        maxArea(
            new int[][] {
              {1, 0},
              {0, 1}
            }));
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Point point = (Point) o;

      if (x != point.x) return false;
      return y == point.y;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      return result;
    }
  }
}
