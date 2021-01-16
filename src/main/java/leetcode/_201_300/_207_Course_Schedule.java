package leetcode._201_300;

import java.util.*;

public class _207_Course_Schedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int dest = prerequisite[0];
            int src = prerequisite[1];
            if (!map.containsKey(src)) {
                map.put(src, new HashSet<>());
            }
            map.get(src).add(dest);
            inDegrees[dest]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;

            if (map.containsKey(current)) {
                for (int adj : map.get(current)) {
                    inDegrees[adj]--;

                    if (inDegrees[adj] == 0) {
                        queue.add(adj);
                    }
                }
            }
        }

        return count == numCourses;
    }


    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

}
