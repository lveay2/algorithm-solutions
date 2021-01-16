package lintcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class _598_Zombie_in_Matrix {

    private static final Integer WALL = 2;
    private static final Integer ZOMBIE = 1;
    private static final Integer HUMAN = 0;
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public static int zombie(int[][] grid) {
        Queue<Point> queue = new LinkedList<>();

        int n = grid.length;
        int m = grid[0].length;

        int human = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == ZOMBIE) {
                    queue.offer(new Point(i, j));
                } else if (grid[i][j] == HUMAN) {
                    human++;
                }
            }
        }

        if (human == 0) {
            return 0;
        }

        int day = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            day++;

            for (int i = 0; i < size; i++) {
                Point cp = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int newX = cp.x + dx[j];
                    int newY = cp.y + dy[j];

                    if (isValid(grid, newX, newY) && grid[newX][newY] == HUMAN) {
                        grid[newX][newY] = ZOMBIE;
                        queue.offer(new Point(newX, newY));
                        human--;
                    }

                    if (human == 0) {
                        return day;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int[][] grid, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void main(String[] args) {
        System.out.println("2 == " + zombie(new int[][]{
                {0, 1, 2, 0, 0},
                {1, 0, 0, 2, 1},
                {0, 1, 0, 0, 0}
        }));
        System.out.println("4 == " + zombie(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));
    }

}
