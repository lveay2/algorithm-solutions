package leetcode._101_200._200_number_of_islands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 *
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 *
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 */
public class NumIslands {

    private static final char ISLAND = '1';
    private static final char WATER = '0';
    private final int[] deltaX = {0, 0, 1, -1};
    private final int[] deltaY = {1, -1, 0, 0};

    public static void main(String[] args) {
        char[][] island1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };

        System.out.println("1 == " + new NumIslands().numIslands(island1));

        char[][] island2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println("3 == " + new NumIslands().numIslands(island2));

        char[][] island3 = {
                {'1', '1'}
        };

        System.out.println("1 == " + new NumIslands().numIslands(island3));
    }

    public int numIslands(char[][] grid) {
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

    private void bfs(char[][] grid, int x, int y) {
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

    private boolean isInBound(Coordinate c, char[][] grid) {
        int m = grid.length;

        int n = grid[0].length;

        return c.x >= 0 && c.x < m && c.y >= 0 && c.y < n;
    }

    class Coordinate {

        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
