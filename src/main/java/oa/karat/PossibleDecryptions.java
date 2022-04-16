package oa.karat;

import java.util.*;

/*
Instead of catching up on the show your friends mounted a brute-force attack and cracked
your code. They are complaining about spoilers again.

This time you choose an ambiguous arbitrary substitution cipher. Assume messages only
contain letters A-Z. Each letter A-Z is mapped to some integer 1-26 (in any order),
and you don't give the order to your friends.

Write a function that given an encrypted string and a list of known words returns all
possible decryptions.

Examples:
dictionary1 = [ 'AXE', 'CAT', 'AT', 'OR', 'A', 'COO', 'CARD']
ciphertext1 = '123'
decrypt(dictionary1, ciphertext1) -> AXE, CAT, AT, OR

123 can be parsed as:
1 2 3 -> 3 distinct letters -> AXE, CAT
12 3 -> 2 distinct letters -> AT, OR
1 23 -> same

ciphertext2 = "122"
decrypt(dictionary1, ciphertext2) -> COO, AT, OR

122 can be parsed as:
1 2 2 -> COO
12 2 -> AT, OR
1 22 -> AT, OR

ciphertext3 = "102"
decrypt(dictionary1, ciphertext3) -> AT, OR

ciphertext4 = "1021"
decrypt(dictionary1, ciphertext4) -> AXE, CAT, AT, OR

ciphertext5 = "1105"
decrypt(dictionary1, ciphertext5) -> AXE, CAT

dictionary2 = [ 'BOXY', 'BORN', 'FORTH', 'FROTH', 'ARTERY', 'ACES', 'PORTO', 'THOR' ]
ciphertext6 = '10251826'
decrypt(dictionary2, ciphertext6) -> ARTERY, ACES, THOR, BORN, BOXY, FORTH, FROTH
*/
public class PossibleDecryptions {
  public static void main(String[] argv) {
    String[] dictionary1 = {"AXE", "CAT", "AT", "OR", "A", "COO", "CARD"};
    String ciphertext1 = "123";
    String ciphertext2 = "122";
    String ciphertext3 = "102";
    String ciphertext4 = "1021";
    String ciphertext5 = "1105";

    String[] dictionary2 = {"BOXY", "BORN", "FORTH", "FROTH", "ARTERY", "ACES", "PORTO", "THOR"};
    String ciphertext6 = "10251826";

    System.out.println(decrypt(dictionary1, ciphertext1));
  }

  private static List<String> decrypt(String[] dictionary, String ciphertext) {
    Map<Integer, List<String>> map = new HashMap<>();
    for (String s: dictionary) {
      int l = s.length();
      map.putIfAbsent(l, new ArrayList<>());
      map.get(l).add(s);
    }

    /*
    root
    1          2        3
    2          3
    3
    */
    List<String> combos = new ArrayList<>();
    dfs(combos, new StringBuilder(), ciphertext, 0);
    System.out.println(combos);

    return null;
  }

  private static void dfs(List<String> combos, StringBuilder sb, String ciphertext, int start) {
    if (start == ciphertext.length()) {
      return;
    }
    combos.add(sb.toString());
    for (int i = start; i < ciphertext.length(); i++) {
      sb.append(ciphertext.charAt(i));
      dfs(combos, sb, ciphertext, start + 1);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
