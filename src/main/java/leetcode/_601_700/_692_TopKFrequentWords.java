package leetcode._601_700;

import java.util.*;

public class _692_TopKFrequentWords {
  public static List<String> topKFrequent(String[] words, int k) {
    List<String> result = new ArrayList<>();
    Map<String, Integer> word2Count = new HashMap<>();
    for (String word : words) {
      if (word2Count.containsKey(word)) {
        word2Count.put(word, word2Count.get(word) + 1);
      } else {
        word2Count.put(word, 1);
      }
    }

    System.out.println(word2Count);

    PriorityQueue<Map.Entry<String, Integer>> queue =
        new PriorityQueue<>(
            (a, b) ->
                Objects.equals(a.getValue(), b.getValue())
                    ? b.getKey().compareTo(a.getKey())
                    : a.getValue() - b.getValue());

    for (Map.Entry<String, Integer> entry : word2Count.entrySet()) {
      queue.offer(entry);
      if (queue.size() > k) {
        queue.poll();
      }
    }

    while (!queue.isEmpty()) {
      result.add(0, queue.poll().getKey());
    }

    return result;
  }

  public static void main(String[] args) {
    List<String> actual =
        topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2);
    List<String> expected = Arrays.asList("i", "love");
    System.out.println(actual + " == " + expected + ": " + expected.equals(actual));
  }
}
