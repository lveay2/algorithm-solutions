package lintcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class _573_Build_Post_Office_II {

    public static int shortestDistance(int[][] grid) {
        int min = Integer.MAX_VALUE;

        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    min = Math.min(min, bfs(grid, n, m, i, j));
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int bfs(int[][] grid, int n, int m, int x, int y) {
        boolean[][] visited = new boolean[n][m];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        int sum = 0;
        int dist = 0;
        while (!queue.isEmpty()) {
            dist++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point cp = queue.poll();
                visited[cp.x][cp.y] = true;

                for (int j = 0; j < 4; j++) {
                    int newX = cp.x + dx[j];
                    int newY = cp.y + dy[j];

                    if (0 <= newX && newX < n && 0 <= newY && newY < m && !visited[newX][newY]) {
                        visited[newX][newY] = true;

                        if (grid[newX][newY] == 0) {
                            queue.offer(new Point(newX, newY));
                        }

                        if (grid[newX][newY] == 1) {
                            sum += dist;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return sum;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) {
        System.out.println(shortestDistance(new int[][]{
                {0, 1, 0, 0, 0}, {1, 0, 0, 2, 1}, {0, 1, 0, 0, 0}
        }));
        System.out.println(shortestDistance(new int[][]{
                {0, 1, 0}, {1, 0, 1}, {0, 1, 0}
        }));
        System.out.println(shortestDistance(new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        }));
    }

}
