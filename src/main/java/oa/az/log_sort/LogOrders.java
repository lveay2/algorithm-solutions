package oa.az.log_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1380. Log Sorting 中文English Given a list of string logs, in which each element representing a
 * log. Each log can be separated into two parts by the first space. The first part is the ID of the
 * log, while the second part is the content of the log. (The content may contain spaces as well.)
 * The content is composed of only letters and spaces, or only numbers and spaces.
 *
 * <p>Now you need to sort logs by following rules:
 *
 * <p>Logs whose content is letter should be ahead of logs whose content is number. Logs whose
 * content is letter should be sorted by their content in lexicographic order. And when two logs
 * have same content, sort them by ID in lexicographic order. Logs whose content is number should be
 * in their input order. 样例 Example 1:
 *
 * <p>Input: logs = [ "zo4 4 7", "a100 Act zoo", "a1 9 2 3 1", "g9 act car" ] Output: [ "a100 Act
 * zoo", "g9 act car", "zo4 4 7", "a1 9 2 3 1" ] Explanation: "Act zoo" < "act car", so the output
 * is as above. Example 2:
 *
 * <p>Input: logs = [ "zo4 4 7", "a100 Actzoo", "a100 Act zoo", "Tac Bctzoo", "Tab Bctzoo", "g9 act
 * car" ] Output: [ "a100 Act zoo", "a100 Actzoo", "Tab Bctzoo", "Tac Bctzoo", "g9 act car", "zo4 4
 * 7" ] Explanation: Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac
 * ", so" Tab Bctzoo "< Tac Bctzoo". Because ' '<'z', so "A100 Act zoo" < A100 Actzoo". 注意事项 The
 * total number of logs will not exceed 5000 The length of each log will now exceed 100 Each log's
 * ID is unique.
 */
public class LogOrders {

  public static void main(String[] args) {
    System.out.println(
        "[a100 Act zoo, g9 act car, zo4 4 7, a1 9 2 3 1] == "
            + Arrays.toString(
                new LogOrders()
                    .logSort(
                        new String[] {"zo4 4 7", "a100 Act zoo", "a1 9 2 3 1", "g9 act car"})));

    System.out.println(
        "[a100 Act zoo, a100 Actzoo, Tab Bctzoo, Tac Bctzoo, g9 act car, zo4 4 7] == "
            + Arrays.toString(
                new LogOrders()
                    .logSort(
                        new String[] {
                          "zo4 4 7",
                          "a100 Actzoo",
                          "a100 Act zoo",
                          "Tac Bctzoo",
                          "Tab Bctzoo",
                          "g9 act car"
                        })));

    System.out.println(
        "[a100 Act zoo, a100 Actzoo, Tab Bctzoo, Tac Bctzoo, g9 act car, zo4 43421451435143213 7] == "
            + Arrays.toString(
                new LogOrders()
                    .logSort(
                        new String[] {
                          "zo4 43421451435143213 7",
                          "a100 Actzoo",
                          "a100 Act zoo",
                          "Tac Bctzoo",
                          "Tab Bctzoo",
                          "g9 act car"
                        })));
  }

  /**
   * @param logs: the logs
   * @return: the log after sorting
   */
  public String[] logSort(String[] logs) {
    // Write your code here
    String[] result = new String[logs.length];

    if (logs == null || logs.length == 0) {
      return result;
    }

    List<String> nums = new ArrayList<>();

    PriorityQueue<String> queue =
        new PriorityQueue<>(
            logs.length,
            (s1, s2) -> {
              int s1FirstSpace = s1.indexOf(" ");

              int s2FirstSpace = s2.indexOf(" ");

              String s1Id = s1.substring(0, s1FirstSpace);

              String s2Id = s2.substring(0, s2FirstSpace);

              String s1Log = s1.substring(s1FirstSpace + 1);

              String s2Log = s2.substring(s2FirstSpace + 1);

              int order = s1Log.compareTo(s2Log);

              if (order == 0) {
                return s1Id.compareTo(s2Id);
              }

              return order;
            });

    for (int i = 0; i < logs.length; i++) {
      String s = logs[i];

      if (isNum(s)) {
        nums.add(s);
      } else {
        queue.offer(s);
      }
    }

    int index = 0;

    while (!queue.isEmpty()) {
      result[index++] = queue.poll();
    }

    for (String s : nums) {
      result[index++] = s;
    }

    return result;
  }

  private boolean isNum(String s) {
    int firstSpace = s.indexOf(" ");

    String log = s.substring(firstSpace + 1);

    String[] logs = log.split(" ");

    return Character.isDigit(logs[0].charAt(0));
  }
}
