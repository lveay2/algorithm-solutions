package lintcode.system_design.mini_cassandra;

import java.util.*;

public class MiniCassandra {

  Map<String, TreeMap<Integer, String>> map;

  public MiniCassandra() {
    map = new HashMap<>();
  }

  public static void main(String[] args) {
    MiniCassandra mc = new MiniCassandra();
    mc.insert("google", 1, "haha");
    System.out.println(mc.query("google", 0, 1) + "\n");

    mc = new MiniCassandra();
    mc.insert("google", 1, "haha");
    mc.insert("lintcode", 1, "Good");
    mc.insert("google", 2, "hehe");
    System.out.println(mc.query("google", 0, 1));
    System.out.println(mc.query("google", 0, 2));
    System.out.println(mc.query("go", 0, 1));
    System.out.println(mc.query("lintcode", 0, 10));
  }

  /*
   * @param raw_key: a string
   * @param column_key: An integer
   * @param column_value: a string
   * @return: nothing
   */
  public void insert(String row_key, int column_key, String value) {
    TreeMap<Integer, String> temp = map.getOrDefault(row_key, new TreeMap<>());
    temp.put(column_key, value);
    map.put(row_key, temp);
  }

  /*
   * @param row_key: a string
   * @param column_start: An integer
   * @param column_end: An integer
   * @return: a list of Columns
   */
  public List<Column> query(String row_key, int column_start, int column_end) {
    List<Column> result = new ArrayList<>();

    if (map.containsKey(row_key)) {
      NavigableMap<Integer, String> temp =
          map.get(row_key).subMap(column_start, true, column_end, true);

      for (Map.Entry<Integer, String> entry : temp.entrySet()) {
        result.add(new Column(entry.getKey(), entry.getValue()));
      }
    }

    return result;
  }
}
