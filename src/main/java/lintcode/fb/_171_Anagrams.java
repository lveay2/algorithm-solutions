package lintcode.fb;

import java.util.*;

public class _171_Anagrams {

    public static List<String> anagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String word = getSortedWord(strs[i]);

            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
            }
            List<String> words = map.get(word);
            words.add(strs[i]);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 2) {
                result.addAll(entry.getValue());
            }
        }

        return result;
    }

    private static String getSortedWord(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(anagrams(new String[]{"lint", "intl", "inlt", "code"}));
        System.out.println(anagrams(new String[]{"ab", "ba", "cd", "dc", "e"}));
    }

}
