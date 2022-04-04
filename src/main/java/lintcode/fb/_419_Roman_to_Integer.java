package lintcode.fb;

import java.util.HashMap;
import java.util.Map;

public class _419_Roman_to_Integer {

  public static int romanToInt(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);

    int l = s.length();
    int result = map.get(s.charAt(l - 1));

    for (int i = l - 2; i >= 0; i--) {
      if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
        result += map.get(s.charAt(i));
      } else {
        result -= map.get(s.charAt(i));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(romanToInt("IV"));
    System.out.println(romanToInt("XCIX"));
  }
}
