package oa.hired;

import java.util.HashSet;

public class NoDuplicateSubstring {

  public static long solution(String s) {
    if (s == null || s.length() == 0) return 0L;

    long maxLength = Long.MIN_VALUE;
    HashSet<Character> charSet = new HashSet<>();
    int start = 0;
    for (int end = 0; end < s.length(); end++) {
      char ec = s.charAt(end);

      while (charSet.contains(ec)) {
        charSet.remove(s.charAt(start));
        start++;
      }
      charSet.add(ec);

      maxLength = Math.max(charSet.size(), maxLength);
    }

    return maxLength;
  }

  public static void main(String[] args) {
    System.out.println(solution("fdsatecGDdasdfgvvhsDFda"));
  }
}
