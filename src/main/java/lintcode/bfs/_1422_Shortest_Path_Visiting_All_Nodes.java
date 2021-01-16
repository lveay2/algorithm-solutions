package lintcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1422_Shortest_Path_Visiting_All_Nodes {

    public static int shortestPathLength(int[][] graph) {
        int len = graph.length;
        boolean[][] book = new boolean[len][1 << len];
        int k = (1 << len) - 1;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{i, 1 << i});
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] node = queue.poll();
                if (k == node[1]) return step;
                for (int next : graph[node[0]]) {
                    int next_state = node[1] | (1 << next);
                    if (book[next][next_state]) continue;
                    book[next][next_state] = true;
                    queue.offer(new int[]{next, next_state});
                }
            }
            step++;
        }
        return step;
    }


    public static void main(String[] args) {
        System.out.println(shortestPathLength(new int[][]{
                {1, 2, 3}, {0}, {0}, {0}
        }));
        System.out.println(shortestPathLength(new int[][]{
                {1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}
        }));
    }

}
