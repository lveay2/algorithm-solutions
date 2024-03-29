package educative.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 *
 * <p>Example 1:
 *
 * <p>Input: "ad52" Output: "ad52", "Ad52", "aD52", "AD52" Example 2:
 *
 * <p>Input: "ab7c" Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 */
public class LetterCaseStringPermutation {

  public static List<String> findLetterCaseStringPermutations(String str) {
    List<String> permutations = new ArrayList<>();
    if (str == null) {
      return permutations;
    }

    permutations.add(str);
    // process every character of the string one by one
    for (int i = 0; i < str.length(); i++) {
      if (Character.isLetter(str.charAt(i))) { // only process characters, skip digits
        // we will take all existing permutations and change the letter case appropriately
        int n = permutations.size();
        for (int j = 0; j < n; j++) {
          char[] chs = permutations.get(j).toCharArray();
          // if the current character is in upper case change it to lower case or vice versa
          if (Character.isUpperCase(chs[i])) {
            chs[i] = Character.toLowerCase(chs[i]);
          } else {
            chs[i] = Character.toUpperCase(chs[i]);
          }
          permutations.add(String.valueOf(chs));
        }
      }
    }
    return permutations;
  }

  public static void main(String[] args) {
    List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
    System.out.println(" String permutations are: [ad52, Ad52, aD52, AD52] == " + result);

    result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
    System.out.println(
        " String permutations are: [ab7c, Ab7c, aB7c, AB7c, ab7C, Ab7C, aB7C, AB7C] == " + result);
  }
}
