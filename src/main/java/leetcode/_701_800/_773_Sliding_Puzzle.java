package leetcode._701_800;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _773_Sliding_Puzzle {

    public static int slidingPuzzle(int[][] board) {
        String target = "123450";

        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int col : row) {
                sb.append(col);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(sb.toString());
        Set<String> set = new HashSet<>();

        int[] d = {1, -1, 3, -3};

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cs = queue.poll();

                if (target.equals(cs)) {
                    return ans;
                }

                int pos = cs.indexOf('0');
                for (int j = 0; j < 4; j++) {
                    int newp = pos + d[j];

                    if (isNotValid(newp, pos)) {
                        continue;
                    }

                    char[] arr = cs.toCharArray();
                    arr[pos] = arr[newp];
                    arr[newp] = '0';

                    String newStr = String.valueOf(arr);
                    if (!set.contains(newStr)) {
                        queue.offer(newStr);
                        set.add(newStr);
                    }
                }

            }

            ans++;
        }

        return -1;
    }

    private static boolean isNotValid(int newp, int pos) {
        return newp < 0 || newp > 5 || (newp == 2 && pos == 3) || (newp == 3 && pos == 2);
    }

    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{
                {1, 2, 3}, {4, 0, 5}
        }));
        System.out.println(slidingPuzzle(new int[][]{
                {1, 2, 3}, {5, 4, 0}
        }));
        System.out.println(slidingPuzzle(new int[][]{
                {4, 1, 2}, {5, 0, 3}
        }));
    }

}
