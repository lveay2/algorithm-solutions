package lintcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1828_Lake_Escape {

  public static boolean lakeEscape(
      int side_length,
      int[][] lake_grid,
      int albert_row,
      int albert_column,
      int kuna_row,
      int kuna_column) {
    if (lake_grid == null || lake_grid.length == 0 || lake_grid[0].length == 0) {
      return false;
    }

    int n = lake_grid.length;
    int m = lake_grid[0].length;

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(albert_row, albert_column, false));

    while (!queue.isEmpty()) {
      Point cp = queue.poll();
      // mark as visited
      lake_grid[cp.x][cp.y] = 2;

      for (int i = 0; i < 4; i++) {
        int cx = cp.x;
        int cy = cp.y;
        boolean foundKuna = cp.foundKuna;

        for (int j = 0; j < side_length; j++) {
          cx += dx[i];
          cy += dy[i];

          // reach to outside of lake
          if (!isValid(cx, cy, n, m)) {
            if (foundKuna) {
              return true;
            }
            break;
          }

          // reach the hole or visited
          if (lake_grid[cx][cy] == -1 || lake_grid[cx][cy] == 2) {
            break;
          }

          // reach snowbank
          if (lake_grid[cx][cy] == 1) {
            if (cx == kuna_row && cy == kuna_column) {
              foundKuna = true;
            }

            queue.offer(new Point(cx, cy, foundKuna));
            // mark as visited
            lake_grid[cx][cy] = 2;
            break;
          }
        }
      }
    }

    return false;
  }

  private static boolean isValid(int x, int y, int n, int m) {
    return 0 <= x && x < n && 0 <= y && y < m;
  }

  public static void main(String[] args) {
    System.out.println(
        lakeEscape(
            7,
            new int[][] {
              {0, 0, 0, 0, 0, 0, 0},
              {0, 0, -1, 0, 0, 0, 0},
              {0, 0, 1, -1, 0, -1, 0},
              {-1, 0, 1, 0, 0, 0, 0},
              {0, 1, 1, 0, 0, 1, 0},
              {-1, 0, -1, 0, -1, 0, 0},
              {0, 0, 0, 0, 0, 0, 0}
            },
            4,
            1,
            3,
            2));
  }

  static class Point {
    int x;
    int y;
    boolean foundKuna;

    Point(int x, int y, boolean foundKuna) {
      this.x = x;
      this.y = y;
      this.foundKuna = foundKuna;
    }
  }
}
