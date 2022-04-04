package oa.transcent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There is a array of words String[] words = {"axyz","yazx", "zxya", "zyxa", "cba", "def",..} etc.
 * So if given an input "axyz", your program should print all the words that contains 'a', 'x', 'y'
 * and 'z' i.e. "axyz","yazx", "zxya", "zyxa", etc
 *
 * <p>number of letter matches order doesn't matter axyz axyz yazx zxya zyxa aaxyz
 *
 * <p>input -> Map<Charater, Integer>
 *
 * <p>'a' - 'a' = diff
 *
 * <p>input a x y z () = product y z a x - 'a' *= product
 *
 * <p>a,a,f 1 - 26
 *
 * <p>25 * a - a 1 3 5 7 11 13 17 19 a - 0 - 1 z - 25 y - a 25 z - a 26
 */
public class word_match {

  static int[] char2Prime =
      new int[] {
        3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31, 37, 29, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
        87, 89
      };

  public static List<String> match(String[] words, String input) {
    if (input == null || input.length() == 0) {
      return Collections.emptyList();
    }

    int targetProduct = calculateProduct(input);

    List<String> result = new ArrayList<>();
    for (String word : words) {
      if (input.length() != word.length()) {
        continue;
      }
      int tempProduct = calculateProduct(input);

      if (tempProduct == targetProduct) {
        result.add(word);
      }
    }

    return result;
  }

  private static int calculateProduct(String s) {
    int targetProduct = 1;
    char[] arr = s.toCharArray();
    for (char c : arr) {
      targetProduct *= char2Prime[c - 'a'];
    }

    return targetProduct;
  }

  public static void main(String[] args) {
    System.out.println(match(new String[] {"axyz", "yazx", "zxya", "zyxa", "cba", "def"}, "axyz"));
  }
}
