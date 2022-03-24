package leetcode._101_200;

import java.util.*;

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land)
and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent
lands horizontally or vertically. You may assume all four edges of the
grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/
public class _200_NumberOfIslands {

    private static final char ISLAND = '1';
    private static final char WATER = '0';
    private static final int[] deltaX = {0, 0, 1, -1};
    private static final int[] deltaY = {1, -1, 0, 0};

    public static void main(String[] args) {
        char[][] island1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("1 == " + numIslands(island1));
        char[][] island2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("3 == " + numIslands(island2));
        char[][] island3 = {{'1', '1'}};
        System.out.println("1 == " + numIslands(island3));

        char[][] island11 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("1 == " + numIslandsDfs(island11));
        char[][] island22 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("3 == " + numIslandsDfs(island22));
        char[][] island33 = {{'1', '1'}};
        System.out.println("1 == " + numIslandsDfs(island33));
    }

    public static int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == ISLAND) {
                    bfs(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    private static void bfs(char[][] grid, int x, int y) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(x, y));
        grid[x][y] = WATER;

        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate adj = new Coordinate(c.x + deltaX[i], c.y + deltaY[i]);

                if (!isInBound(adj, grid)) {
                    continue;
                }

                if (grid[adj.x][adj.y] == ISLAND) {
                    grid[adj.x][adj.y] = WATER;
                    queue.offer(adj);
                }
            }
        }
    }

    private static boolean isInBound(Coordinate c, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return c.x >= 0 && c.x < m && c.y >= 0 && c.y < n;
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int numIslandsDfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }

        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }

}
