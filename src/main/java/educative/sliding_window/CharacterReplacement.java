package educative.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’
 * letters with any letter, find the length of the longest substring having the same letters after
 * replacement.
 * <p>
 * Example 1:
 * <p>
 * Input: String="aabccbb", k=2 Output: 5 Explanation: Replace the two 'c' with 'b' to have a
 * longest repeating substring "bbbbb".
 * <p>
 * Example 2:
 * <p>
 * Input: String="abbcb", k=1 Output: 4 Explanation: Replace the 'c' with 'b' to have a longest
 * repeating substring "bbbb".
 * <p>
 * Example 3:
 * <p>
 * Input: String="abccde", k=1 Output: 3 Explanation: Replace the 'b' or 'd' with 'c' to have the
 * longest repeating substring "ccc".
 */
public class CharacterReplacement {

    public static int findLength(String str, int k) {
        int maxLength = 0;
        int maxRepeating = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int l = str.length();

        for (int end = 0; end < l; end++) {
            char endC = str.charAt(end);
            map.put(endC, map.getOrDefault(endC, 0) + 1);
            maxRepeating = Math.max(maxRepeating, map.get(endC));

            while (end - start + 1 - maxRepeating > k) {
                char startC = str.charAt(start);
                map.put(startC, map.get(startC) - 1);
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(CharacterReplacement.findLength("aabccbb", 2));
        System.out.println(CharacterReplacement.findLength("abbcb", 1));
        System.out.println(CharacterReplacement.findLength("abccde", 1));
    }

}
