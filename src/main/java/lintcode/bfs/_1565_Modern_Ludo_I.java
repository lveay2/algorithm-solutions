package lintcode.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _1565_Modern_Ludo_I {

  public static int modernLudo(int length, int[][] connections) {
    int[] dis = new int[length + 1];
    Arrays.fill(dis, Integer.MAX_VALUE);
    dis[1] = 0;

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(1);

    while (!queue.isEmpty()) {
      int head = queue.poll();

      // update next 6 in dis
      for (int i = 1; i <= 6; i++) {
        if (head + i <= length && dis[head + i] > dis[head] + 1) {
          queue.offer(head + i);
          dis[head + i] = dis[head] + 1;
        }
      }

      // update connection end in dis
      for (int i = 0; i < connections.length; i++) {
        if (head == connections[i][0] && dis[connections[i][1]] > dis[head]) {
          dis[connections[i][1]] = dis[head];
          if (!queue.contains(connections[i][1])) {
            queue.offer(connections[i][1]);
          }
        }
      }
    }

    return dis[length];
  }

  public static void main(String[] args) {
    System.out.println(modernLudo(10, new int[][] {{2, 10}}));
    System.out.println(modernLudo(15, new int[][] {{7, 9}, {8, 14}}));
  }
}
