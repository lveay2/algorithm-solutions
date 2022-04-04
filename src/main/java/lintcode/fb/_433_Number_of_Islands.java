package lintcode.fb;

import java.util.LinkedList;
import java.util.Queue;

public class _433_Number_of_Islands {

  public static int numIslands2(boolean[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int ans = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j]) {
          ans++;
          markByBfs2(grid, i, j);
        }
      }
    }

    return ans;
  }

  private static void markByBfs2(boolean[][] grid, int i, int j) {
    grid[i][j] = false;
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, 1, -1, 0};

    Queue<Integer> qx = new LinkedList<>();
    Queue<Integer> qy = new LinkedList<>();
    qx.offer(i);
    qy.offer(j);

    while (!qx.isEmpty() && !qy.isEmpty()) {
      int cx = qx.poll();
      int cy = qy.poll();

      for (int k = 0; k < 4; k++) {
        int nx = cx + dx[k];
        int ny = cy + dy[k];

        if (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && grid[nx][ny]) {
          qx.offer(nx);
          qy.offer(ny);
          grid[nx][ny] = false;
        }
      }
    }
  }

  public static int numIslands(boolean[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int ans = 0;
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] && !visited[i][j]) {
          ans++;
          markByBfs(grid, i, j, visited);
        }
      }
    }

    return ans;
  }

  private static void markByBfs(boolean[][] grid, int i, int j, boolean[][] visited) {
    visited[i][j] = true;
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, 1, -1, 0};

    Queue<Integer> qx = new LinkedList<>();
    Queue<Integer> qy = new LinkedList<>();
    qx.offer(i);
    qy.offer(j);

    while (!qx.isEmpty()) {
      int cx = qx.poll();
      int cy = qy.poll();

      for (int k = 0; k < 4; k++) {
        int nx = cx + dx[k];
        int ny = cy + dy[k];

        if (0 <= nx
            && nx < grid.length
            && 0 <= ny
            && ny < grid[0].length
            && !visited[nx][ny]
            && grid[nx][ny]) {
          qx.offer(nx);
          qy.offer(ny);
          visited[nx][ny] = true;
        }
      }
    }
  }

  public static int numIslands3(boolean[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int x = grid.length;
    int y = grid[0].length;

    int ans = 0;
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        if (grid[i][j]) {
          ans++;
          dfs(grid, i, j);
        }
      }
    }

    return ans;
  }

  private static void dfs(boolean[][] grid, int i, int j) {
    if (0 > i || i >= grid.length || 0 > j || j >= grid[0].length || !grid[i][j]) {
      return;
    }

    grid[i][j] = false;

    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
  }

  public static void main(String[] args) {
    System.out.println(numIslands(new boolean[][] {{true, true}}));
    System.out.println(
        numIslands(
            new boolean[][] {
              {true, true, false, false, false},
              {false, true, false, false, true},
              {false, false, false, true, true},
              {false, false, false, false, false},
              {false, false, false, false, true}
            }));
    System.out.println(numIslands2(new boolean[][] {{true, true}}));
    System.out.println(
        numIslands2(
            new boolean[][] {
              {true, true, false, false, false},
              {false, true, false, false, true},
              {false, false, false, true, true},
              {false, false, false, false, false},
              {false, false, false, false, true}
            }));
    System.out.println(numIslands3(new boolean[][] {{true, true}}));
    System.out.println(
        numIslands3(
            new boolean[][] {
              {true, true, false, false, false},
              {false, true, false, false, true},
              {false, false, false, true, true},
              {false, false, false, false, false},
              {false, false, false, false, true}
            }));
  }
}
