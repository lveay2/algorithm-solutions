package lintcode.twopointers;

public class _1375_Substring_With_At_Least_K_Distinct_Characters {

  public static long kDistinctCharacters(String s, int k) {
    int[] cc = new int[26];
    int count = 0;

    int left = 0, right = 0;
    long ans = 0;

    int l = s.length();
    while (left <= right && left < l) {
      while (count < k && right < l) {
        cc[s.charAt(right) - 'a']++;
        if (cc[s.charAt(right) - 'a'] == 1) {
          count++;
        }
        right++;
      }

      if (count == k) {
        ans += l - right + 1;
      }

      if (cc[s.charAt(left) - 'a'] == 1) {
        count--;
      }
      cc[s.charAt(left) - 'a']--;
      left++;
    }

    return ans;
  }

  public static void main(String[] args) {
    System.out.println("55 == " + kDistinctCharacters("abcabcabcabc", 3));
    System.out.println(
        "5050 == "
            + kDistinctCharacters(
                "veunvywzrejbyawhzkwzraafgdjoefevaczcjfdknpjdyqhttizpngweiqefbdtzgizxwfvaakeglpldjelvdbuhwcgkjnyzlxsz",
                1));
    System.out.println(
        "4947 == "
            + kDistinctCharacters(
                "bkacsrgjkynenkwbfaeblmbjfjshojryawvsuftmqftkjqnsjfhvbsddwiwmifhphftkckkedzotkbiplqjsgdyqgjnclwjqxbbc",
                2));
  }
}
