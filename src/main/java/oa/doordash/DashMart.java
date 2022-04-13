package oa.doordash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

A DashMart is a warehouse run by DoorDash that houses items
found in convenience stores, grocery stores, and restaurants.
We have a city with open roads, blocked-off roads, and DashMarts.

City planners want you to identify how far a location is from its
closest DashMart.

You can only travel over open roads (up, down, left, right).

Locations are given in [row, col] format.

[
#     0    1    2    3    4    5    6    7    8
    ['X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'], # 0
    ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
    [' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '], # 2
    [' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '], # 3
    [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
    [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X']  # 5
]

' ' represents an open road that you can travel over in any direction (up, down, left, or right).
'X' represents an blocked road that you cannot travel through.
'D' represents a DashMart.

# list of pairs [row, col]
locations = [
    [200, 200],
    [1, 4],
    [0, 3],
    [5, 8],
    [1, 8],
    [5, 5]
]

answer = [-1, 2, 0, -1, 6, 9]

Provided:
- city: char[][]
- locations: int[][]

Return:
- answer: int[]
 */
public class DashMart {

    public static void main(String[] args) {
        System.out.println("test1");
        char[][] city = {
                //0    1    2    3    4    5    6    7    8
                {'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'}, // 0
                {'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'}, // 1
                {' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '}, // 2
                {' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '}, // 3
                {' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'}, // 4
                {' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'}  // 5
        };

        int[][] locations = {{200, 200}, {1, 4}, {0, 3}, {5, 8}, {1, 8}, {5, 5}};

        int[] result = searchDistance(city, locations);
        System.out.println("result: " + Arrays.toString(result));

        int[] expectedAnswer = {-1, 2, 0, -1, 6, 9};
        System.out.println(
                Arrays.equals(expectedAnswer, result)
                        ? "Passed test 1" : "X Failed test 1"
        );

        System.out.println("\ntest2");
        char[][] city2 = {
                // 0    1    2
                {'D', 'X', 'X'}, // 0
                {'D', 'D', 'X'}  // 1
        };

        int[][] locations2 = {{0, 2}, {1, 1}, {1, 2}};

        int[] result2 = searchDistance(city2, locations2);
        System.out.println("result: " + Arrays.toString(result2));

        int[] expectedAnswer2 = {-1, 0, 1};
        System.out.println(
                Arrays.equals(expectedAnswer2, result2)
                        ? "Passed test 2" : "X Failed test 2"
        );

        System.out.println("\ntest3");
        char[][] city3 = {
                //0    1    2    3    4    5    6    7    8
                {' ', ' ', ' ', ' ', ' ', 'X', 'X', ' ', ' '}, // 0
                {'X', 'X', 'X', ' ', ' ', 'X', ' ', ' ', 'X'}  // 1
        };
        int[][] locations3 = {{0, 3}, {0, 5}, {1, 8}};

        int[] result3 = searchDistance(city3, locations3);
        System.out.println("result: " + Arrays.toString(result3));

        int[] expectedAnswer3 = {-1, -1, -1};
        System.out.println(
                Arrays.equals(result3, expectedAnswer3)
                        ? "Passed test 3" : "X Failed test 3"
        );
    }

    private static void printMatrix(int[][] distances) {
        for (int[] row : distances) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[] searchDistance(char[][] city, int[][] locations) {
        int m = city.length;
        int n = city[0].length;

        int[][] distances = new int[m][n];
        for (int[] row : distances) {
            Arrays.fill(row, -1);
        }
        build(city, distances);
        printMatrix(distances);

        int[] result = new int[locations.length];
        for (int i = 0; i < locations.length; i++) {
            int x = locations[i][0];
            int y = locations[i][1];

            if (x < 0 || x >= m || y < 0 || y >= n) {
                result[i] = -1;
                continue;
            }

            result[i] = distances[x][y];
        }

        return result;
    }

    /*
    ' ' represents an open road that you can travel over in any direction (up, down, left, or right).
    'X' represents an blocked road that you cannot travel through.
    'D' represents a DashMart.
    */
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void build(char[][] city, int[][] distances) {
        int m = city.length;
        int n = city[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (city[i][j] == 'D') {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int qs = queue.size();

            for (int i = 0; i < qs; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                distances[x][y] = count;
                if (city[x][y] == 'X') {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    int newx = x + dx[j];
                    int newy = y + dy[j];

                    if (newx < 0 || newx >= m || newy < 0 || newy >= n) {
                        continue;
                    }
                    if (city[newx][newy] == 'D') {
                        continue;
                    }
                    //.D '1' '1' D
                    if (distances[newx][newy] == -1) {
                        queue.add(new int[]{newx, newy});
                    }
                }
            }
            count++;
        }
    }
}
