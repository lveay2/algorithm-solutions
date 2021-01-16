package lintcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class _677_Number_of_Big_Islands {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static int numsofIsland(boolean[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    if (bfs(grid, i, j) >= k) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private static int bfs(boolean[][] grid, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;

        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        grid[x][y] = false;

        int count = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (0 <= newX && newX < n && 0 <= newY && newY < m && grid[newX][newY]) {
                    queue.add(new Point(newX, newY));
                    grid[newX][newY] = false;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numsofIsland(buildIsland("[[1,1,0,0,0],[0,1,0,0,1],[0,0,0,1,1],[0,0,0,0,0],[0,0,0,0,1]]"), 5));
        System.out.println(numsofIsland(buildIsland("[[1,1,0,0,0],[0,1,0,0,1],[0,0,0,1,1],[0,0,0,0,0],[0,0,0,0,1]]"), 2));
        System.out.println(numsofIsland(buildIsland("[[1,0],[0,1]]"), 1));
    }

    private static boolean[][] buildIsland(String s) {
        s = s.substring(1, s.length() - 2);
        String[] rows = s.split("\\]\\,");

        int n = rows.length;
        int m = (int) rows[0].chars().filter(ch -> ch == ',').count();

        boolean[][] result = new boolean[n][m + 1];
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            row = row.substring(1);
//            System.out.println("i: " + i + ", row: " + row);
            String[] columns = row.split("\\,");
            for (int j = 0; j < columns.length; j++) {
                String col = columns[j];
//                System.out.println("j: " + j + ", col: " + col);
                if (col.equals("1")) {
                    result[i][j] = true;
                } else {
                    result[i][j] = false;
                }
            }
        }

//        System.out.println(Arrays.deepToString(result));
        return result;
    }

}
