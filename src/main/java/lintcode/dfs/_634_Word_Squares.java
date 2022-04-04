package lintcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _634_Word_Squares {

  public static void main(String[] args) {
    _634_Word_Squares solution = new _634_Word_Squares();
    System.out.println(solution.wordSquares(new String[] {"area", "lead", "wall", "lady", "ball"}));
    System.out.println(solution.wordSquares(new String[] {"abat", "baba", "atan", "atal"}));
  }

  public List<List<String>> wordSquares(String[] words) {
    Map<String, List<String>> prefixs = buildMap(words);

    List<List<String>> result = new ArrayList<>();
    for (String word : words) {
      List<String> square = new ArrayList<>();
      square.add(word);
      search(square, prefixs, result);
    }

    return result;
  }

  private void search(
      List<String> square, Map<String, List<String>> prefixs, List<List<String>> result) {
    int currentIndex = square.size();
    int n = square.get(0).length();
    if (currentIndex == n) {
      result.add(new ArrayList<>(square));
      return;
    }

    for (int rowIndex = currentIndex; rowIndex < n; rowIndex++) {
      StringBuilder prefix = new StringBuilder();
      for (int i = 0; i < currentIndex; i++) {
        prefix.append(square.get(i).charAt(rowIndex));
      }
      if (!prefixs.containsKey(prefix.toString())) {
        return;
      }
    }

    StringBuilder prefix = new StringBuilder();
    for (int i = 0; i < currentIndex; i++) {
      prefix.append(square.get(i).charAt(currentIndex));
    }

    for (String word : prefixs.getOrDefault(prefix.toString(), new ArrayList<>())) {
      square.add(word);
      search(square, prefixs, result);
      square.remove(square.size() - 1);
    }
  }

  private Map<String, List<String>> buildMap(String[] words) {
    Map<String, List<String>> prefixs = new HashMap<>();
    for (String word : words) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < word.length(); i++) {
        sb.append(word.charAt(i));
        String prefix = sb.toString();
        if (!prefixs.containsKey(prefix)) {
          prefixs.put(prefix, new ArrayList<>());
        }
        prefixs.get(prefix).add(word);
      }
    }

    return prefixs;
  }
}
