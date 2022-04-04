package leetcode;

import java.util.*;

/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter
 * represents a different task. Tasks could be done in any order. Each task is done in one unit of
 * time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * <p>However, there is a non-negative integer n that represents the cooldown period between two
 * same tasks (the same letter in the array), that is that there must be at least n units of time
 * between any two same tasks.
 *
 * <p>Return the least number of units of times that the CPU will take to finish all the given
 * tasks.
 *
 * <p>Example 1:
 *
 * <p>Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8 Explanation: A -> B -> idle -> A ->
 * B -> idle -> A -> B There is at least 2 units of time between any two same tasks.
 *
 * <p>Example 2:
 *
 * <p>Input: tasks = ["A","A","A","B","B","B"], n = 0 Output: 6 Explanation: On this case any
 * permutation of size 6 would work since n = 0. ["A","A","A","B","B","B"] ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"] ... And so on.
 *
 * <p>Example 3:
 *
 * <p>Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2 Output: 16
 * Explanation: One possible solution is A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle ->
 * idle -> A -> idle -> idle -> A
 *
 * <p>Constraints:
 *
 * <p>1 <= task.length <= 104 tasks[i] is upper-case English letter. The integer n is in the range
 * [0, 100].
 */
public class _621_Task_Scheduler {

  public static int leastInterval(char[] tasks, int n) {
    Map<Character, Integer> c2c = new HashMap<>();

    for (char task : tasks) {
      c2c.put(task, c2c.getOrDefault(task, 0) + 1);
    }

    Queue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
    queue.addAll(c2c.values());

    int result = 0;
    int group = n + 1;
    while (!queue.isEmpty()) {
      int time = 0;
      List<Integer> tempList = new ArrayList<>();

      for (int i = 0; i < group; i++) {
        if (!queue.isEmpty()) {
          tempList.add(queue.poll());
          time++;
        }
      }

      for (int count : tempList) {
        count--;
        if (count > 0) {
          queue.offer(count);
        }
      }

      result += !queue.isEmpty() ? group : time;
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(leastInterval(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    System.out.println(leastInterval(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 0));
    System.out.println(
        leastInterval(new char[] {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
  }
}
