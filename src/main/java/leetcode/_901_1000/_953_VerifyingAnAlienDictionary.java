package leetcode._901_1000;

/*
953. Verifying an Alien Dictionary
In an alien language, surprisingly, they also use English lowercase letters,
but possibly in a different order. The order of the alphabet is some permutation
of lowercase letters.

Given a sequence of words written in the alien language, and the order of the
alphabet, return true if and only if the given words are sorted lexicographically
in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1],
hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is
shorter (in size.) According to lexicographical rules "apple" > "app", because
'l' > '∅', where '∅' is defined as the blank character which is less than any
other character (More info).

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */
public class _953_VerifyingAnAlienDictionary {

  public static void main(String[] args) {
    System.out.println(
        "true == "
            + isAlienSorted(new String[] {"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    System.out.println(
        "false == "
            + isAlienSorted(new String[] {"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    System.out.println(
        "false == " + isAlienSorted(new String[] {"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
  }

  public static boolean isAlienSorted(String[] words, String order) {
    int[] orders = new int[26];
    for (int i = 0; i < order.length(); i++) {
      orders[order.charAt(i) - 'a'] = i;
    }

    for (int i = 1; i < words.length; i++) {
      if (compare(words[i - 1], words[i], orders) > 0) {
        return false;
      }
    }

    return true;
  }

  private static int compare(String a, String b, int[] orders) {
    int m = a.length();
    int n = b.length();
    int min = Math.min(m, n);
    for (int i = 0; i < min; i++) {
      int ao = orders[a.charAt(i) - 'a'];
      int bo = orders[b.charAt(i) - 'a'];
      if (ao != bo) {
        return ao - bo;
      }
    }

    return m - n;
  }
}
