package leetcode._301_400;

public class _387_First_Unique_Character_in_a_String {

  public static void main(String[] args) {
    _387_First_Unique_Character_in_a_String solution =
        new _387_First_Unique_Character_in_a_String();
    System.out.println(solution.firstUniqChar("leetcode"));
    System.out.println(solution.firstUniqChar("loveleetcode"));
  }

  public int firstUniqChar(String s) {
    int[] freq = new int[26];

    for (int i = 0; i < s.length(); i++) {
      freq[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s.length(); i++) {
      if (freq[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }

    return -1;
  }
}
