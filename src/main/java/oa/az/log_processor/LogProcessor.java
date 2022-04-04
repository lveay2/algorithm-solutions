package oa.az.log_processor;

import java.util.*;

class LogProcessor {

  /*
   * Complete the 'processLogs' function below.
   *
   * The function is expected to return a STRING_ARRAY.
   * The function accepts following parameters:
   *  1. STRING_ARRAY logs
   *  2. INTEGER threshold
   */
  public static List<String> processLogs(List<String> logs, int threshold) {
    if (logs == null || logs.size() == 0) {
      return Collections.emptyList();
    }

    List<String> result = new ArrayList<>();
    Map<String, Integer> id2Tran = new HashMap<>();

    for (int i = 0; i < logs.size(); i++) {
      String log = logs.get(i);
      String[] logArr = log.split(" ");
      String id1 = logArr[0];
      String id2 = logArr[1];

      if (id1.equals(id2)) {
        id2Tran.put(id1, id2Tran.getOrDefault(id1, 0) + 1);
      } else {
        id2Tran.put(id1, id2Tran.getOrDefault(id1, 0) + 1);
        id2Tran.put(id2, id2Tran.getOrDefault(id2, 0) + 1);
      }
    }

    for (Map.Entry<String, Integer> e : id2Tran.entrySet()) {
      if (e.getValue() >= threshold) {
        result.add(e.getKey());
      }
    }

    result.sort(Comparator.comparingInt(Integer::valueOf));
    return result;
  }

  public static void main(String[] args) {
    System.out.println(
        processLogs(Arrays.asList("114 112 200", "112 114 150", "12 12 15", " 50 112 200"), 2));
  }
}
