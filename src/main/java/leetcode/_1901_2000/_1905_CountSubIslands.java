package leetcode._1901_2000;

/*
1905. Count Sub Islands
You are given two m x n binary matrices grid1 and grid2 containing
only 0's (representing water) and 1's (representing land). An island
is a group of 1's connected 4-directionally (horizontal or vertical).
Any cells outside the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island
in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.


Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

Constraints:
m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
 */
public class _1905_CountSubIslands {

  public static void main(String[] args) {
    int[][] grid1 = {
      {1, 1, 1, 0, 0},
      {0, 1, 1, 1, 1},
      {0, 0, 0, 0, 0},
      {1, 0, 0, 0, 0},
      {1, 1, 0, 1, 1}
    };
    int[][] grid2 = {
      {1, 1, 1, 0, 0},
      {0, 0, 1, 1, 1},
      {0, 1, 0, 0, 0},
      {1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0}
    };
    System.out.println("3 == " + countSubIslands(grid1, grid2));

    int[][] grid3 = {
      {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}
    };
    int[][] grid4 = {
      {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}
    };
    System.out.println("2 == " + countSubIslands(grid3, grid4));
  }

  public static int countSubIslands(int[][] grid1, int[][] grid2) {
    int m = grid1.length;
    int n = grid1[0].length;

    int ans = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid1[i][j] == 0 && grid2[i][j] == 1) {
          dfs(grid2, i, j);
        }
      }
    }
    //        System.out.println(Arrays.deepToString(grid2));

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid2[i][j] == 1) {
          dfs(grid2, i, j);
          ans++;
        }
      }
    }

    return ans;
  }

  private static void dfs(int[][] grid, int i, int j) {
    //        System.out.println(i + " " + j + "\n" + Arrays.deepToString(grid));
    int m = grid.length;
    int n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (grid[i][j] == 0) {
      return;
    }

    grid[i][j] = 0;
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
  }
}
