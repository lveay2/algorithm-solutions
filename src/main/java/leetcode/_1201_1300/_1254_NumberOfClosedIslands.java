package leetcode._1201_1300;

/*
Given a 2D grid consists of 0s (land) and 1s (water).
An island is a maximal 4-directionally connected group
of 0s and a closed island is an island totally (all
left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2


Constraints:
1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
*/
public class _1254_NumberOfClosedIslands {

  public static void main(String[] args) {
    int[][] grid =
        new int[][] {
          {1, 1, 1, 1, 1, 1, 1},
          {1, 0, 0, 0, 0, 0, 1},
          {1, 0, 1, 1, 1, 0, 1},
          {1, 0, 1, 0, 1, 0, 1},
          {1, 0, 1, 1, 1, 0, 1},
          {1, 0, 0, 0, 0, 0, 1},
          {1, 1, 1, 1, 1, 1, 1}
        };
    System.out.println("2 == " + closedIsland(grid));

    int[][] grid2 = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}};
    System.out.println("1 == " + closedIsland(grid2));

    int[][] grid3 = {
      {1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 1},
      {1, 0, 1, 1, 1, 0, 1},
      {1, 0, 1, 0, 1, 0, 1},
      {1, 0, 1, 1, 1, 0, 1},
      {1, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1}
    };
    System.out.println("2 == " + closedIsland(grid3));
  }

  public static int closedIsland(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    for (int j = 0; j < n; j++) {
      dfs(grid, 0, j);
      dfs(grid, m - 1, j);
    }
    for (int i = 0; i < m; i++) {
      dfs(grid, i, 0);
      dfs(grid, i, n - 1);
    }
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          ans++;
          dfs(grid, i, j);
        }
      }
    }

    return ans;
  }

  private static void dfs(int[][] grid, int i, int j) {
    int m = grid.length;
    int n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (grid[i][j] == 1) {
      return;
    }

    grid[i][j] = 1;
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
  }
}
