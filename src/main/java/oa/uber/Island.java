package oa.uber;

import java.util.*;

public class Island {
  String[][] matrix;
  int count;
  int r;
  int c;

  public static void main(String[] args) {
    Island island = new Island(5, 4);
    int[][] coordinates = new int[][] {{0, 0}, {3, 3}, {1, 1}, {0, 1}};
    System.out.println(Arrays.toString(island.process(coordinates)));
  }

  public Island(int r, int c) {
    this.matrix = new String[r][c];
    this.count = 0;
    this.r = r;
    this.c = c;

    for (int i = 0; i < r; i++) {
      String[] row = new String[c];
      Arrays.fill(row, "X");
      matrix[i] = row;
    }

    this.print();
  }

  public void print() {
    System.out.println("------------------");
    for (String[] strings : matrix) {
      System.out.println(Arrays.toString(strings));
    }
  }

  // k coordinates
  // 1 2 3 4
  public int[] process(int[][] coordinates) {
    if (coordinates == null || coordinates.length == 0) {
      return new int[0];
    }
    int l = coordinates.length;
    int[] result = new int[l];

    for (int i = 0; i < l; i++) {
      int x = coordinates[i][0];
      int y = coordinates[i][1];

      this.matrix[x][y] = "1";
      if (hasNoNeighborConnected(x, y)) {
        this.count++;
        result[i] = this.count;
        this.print();
        continue;
      }
      // total count of island - number of island connected + 1
      boolean[][] visited = new boolean[r][c];
      this.count = 0;
      for (int row = 0; row < this.r; row++) {
        for (int col = 0; col < this.c; col++) {
          if (this.matrix[row][col] == "1" && !visited[row][col]) {
            dfs(row, col, visited);
            this.count++;
          }
        }
      }
      this.print();
      result[i] = this.count;
    }

    System.out.println("------------------");
    return result;
  }

  private static final int[] dx = {0, 1, 0, -1};
  private static final int[] dy = {1, 0, -1, 0};

  private boolean hasNoNeighborConnected(int x, int y) {
    for (int i = 0; i < 4; i++) {
      int newX = x + dx[i];
      int newY = y + dy[i];

      if (!isValid(newX, newY)) {
        continue;
      }

      if (isValid(newX, newY) && !this.matrix[newX][newY].equals("X")) {
        return false;
      }
    }

    return true;
  }

  private void dfs(int x, int y, boolean[][] visited) {
    if (!isValid(x, y) || visited[x][y]) {
      return;
    }

    visited[x][y] = true;

    dfs(x + 1, y, visited);
    dfs(x - 1, y, visited);
    dfs(x, y + 1, visited);
    dfs(x, y - 1, visited);
  }

  private boolean isValid(int x, int y) {
    return x >= 0 && x < this.r && y >= 0 && y < this.c;
  }
}
