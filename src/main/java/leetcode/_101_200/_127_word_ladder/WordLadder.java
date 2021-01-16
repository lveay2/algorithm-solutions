package leetcode._101_200._127_word_ladder;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 */
public class WordLadder {

    public static void main(String[] args) {
        System.out.println(new WordLadder().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));

        System.out.println(new WordLadder().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

        System.out.println(new WordLadder().ladderLength("leet", "code", Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == endWord) {
            return 1;
        }

        if (wordList == null || wordList.size() == 0) {
            return 0;
        }

        Set<String> dict = new HashSet<>();

        for (String s : wordList) {
            if (s.equals(beginWord)) {
                continue;
            }

            dict.add(s);
        }

        if (!dict.contains(endWord)) {
            return 0;
        }


        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        Set<String> set = new HashSet<>();

        set.add(beginWord);

        int length = 1;

        while (!queue.isEmpty()) {
            length++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String temp = queue.poll();

                List<String> nextWords = getNextWords(temp, dict);

                for (String word : nextWords) {
                    if (set.contains(word)) {
                        continue;
                    }

                    if (word.equals(endWord)) {
                        return length;
                    }

                    queue.offer(word);

                    set.add(word);
                }

//                System.out.println("set: " + set);
            }
        }

        return 0;
    }

    private List<String> getNextWords(String temp, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();

        for (int i = 0; i < temp.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == temp.charAt(i)) {
                    continue;
                }

                String newWord = replace(temp, i, c);

                if (dict.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }

        System.out.println(nextWords);

        return nextWords;
    }

    private String replace(String temp, int i, char c) {
        char[] temps = temp.toCharArray();

        temps[i] = c;

        return new String(temps);
    }

}
