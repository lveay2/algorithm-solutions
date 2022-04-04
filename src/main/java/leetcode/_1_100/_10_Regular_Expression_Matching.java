package leetcode._1_100;

public class _10_Regular_Expression_Matching {

  public static boolean isMatch(String s, String p) {
    if (p.length() == 0) {
      return s.length() == 0;
    }

    boolean isFirstMatched = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

    if (p.length() >= 2 && p.charAt(1) == '*') {
      return isMatch(s, p.substring(2)) || (isFirstMatched && isMatch(s.substring(1), p));
    } else {
      return isFirstMatched && isMatch(s.substring(1), p.substring(1));
    }
  }

  public static void main(String[] args) {
    System.out.println(isMatch("aa", "a"));
    System.out.println(isMatch("aa", "a*"));
    System.out.println(isMatch("aab", "c*a*b"));
    System.out.println(isMatch("mississippi", "mis*is*p*."));
  }
}
