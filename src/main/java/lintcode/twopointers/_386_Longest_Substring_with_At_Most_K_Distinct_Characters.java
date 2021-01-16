package lintcode.twopointers;

public class _386_Longest_Substring_with_At_Most_K_Distinct_Characters {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int ans = 0;

        int[] counts = new int[58];
        int count = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
//      System.out.println(end + "," +s.charAt(end) + "," + (s.charAt(end) - 'a') + "," + Arrays.toString(counts));
            counts[s.charAt(end) - 'A']++;
            if (counts[s.charAt(end) - 'A'] == 1) {
                count++;
            }

            while (count > k) {
                if (counts[s.charAt(start) - 'A'] == 1) {
                    count--;
                }
                counts[s.charAt(start) - 'A']--;
                start++;
            }

            ans = Math.max(ans, end - start + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("4 == " + lengthOfLongestSubstringKDistinct("eceba", 3));
        System.out.println("4 == " + lengthOfLongestSubstringKDistinct("WORLD", 4));
        System.out.println("1 == " + lengthOfLongestSubstringKDistinct("W", 1));
        System.out.println("27 == " + lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16));
        System.out.println("21 == " + lengthOfLongestSubstringKDistinct("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));
    }

}
