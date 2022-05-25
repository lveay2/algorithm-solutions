package leetcode._201_300;

import java.util.*;

/*
269. Alien Dictionary

There is a new alien language that uses the English alphabet. However,
the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary,
where the strings in words are sorted lexicographically by the rules of
this new language.

Return a string of the unique letters in the new alien language sorted in
lexicographically increasing order by the new language's rules. If there
is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first
letter where they differ, the letter in s comes before the letter in t
in the alien language. If the first min(s.length, t.length) letters are
the same, then s is smaller if and only if s.length < t.length.

Example 1:
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:
Input: words = ["z","x"]
Output: "zx"

Example 3:
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */
public class _269_AlienDictionary {

  public static void main(String[] args) {
    System.out.println("abc == " + alienOrder(Arrays.asList("a", "b", "c")));
    System.out.println("abruepc == " + alienOrder(Arrays.asList("uber", "car", "cap")));
    System.out.println(" == " + alienOrder(Arrays.asList("uber", "car", "cap", "car")));
    System.out.println("wertf == " + alienOrder(Arrays.asList("wrt", "wrf", "er", "ett", "rftt")));
    System.out.println("zx == " + alienOrder(Arrays.asList("z", "x")));
    System.out.println(" == " + alienOrder(Arrays.asList("z", "x", "z")));
    System.out.println("z == " + alienOrder(Arrays.asList("z", "z")));
    System.out.println("z == " + alienOrder(Arrays.asList("z", "z", "z")));
    System.out.println("wrtkj == " + alienOrder(Arrays.asList("wrt", "wrtkj")));
    System.out.println(" == " + alienOrder(Arrays.asList("wrtkj", "wrt")));
    System.out.println("azcb == " + alienOrder(Arrays.asList("za","zb","ca","cb")));
  }

  public static String alienOrder(List<String> inputs) {
    Map<Character, Integer> indegrees = new HashMap<>();
    Set<Character> allLetters = findAllLetters(inputs, indegrees);

    Map<Character, Set<Character>> graph = buildGraph(inputs, indegrees);
//    System.out.println("indegrees: " + indegrees);

    if (graph.isEmpty()) {
      return "";
    }

    Queue<Character> queue = new LinkedList<>();
    Set<Character> visited = new HashSet<>();
    for (Map.Entry<Character, Integer> e : indegrees.entrySet()) {
      if (e.getValue() == 0) {
        queue.offer(e.getKey());
        visited.add(e.getKey());
      }
    }
//    System.out.println("queue: " + queue);
//    System.out.println("visited: " + visited);

    StringBuilder sb = new StringBuilder();

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        Character c = queue.poll();
        sb.append(c);
//        System.out.println("result: " + result);

        Set<Character> neighbors = graph.get(c);
        if (neighbors == null || neighbors.isEmpty()) {
          continue;
        }
        for (Character nc : neighbors) {
          indegrees.put(nc, indegrees.get(nc) - 1);
          if (indegrees.get(nc) == 0 && !visited.contains(nc)) {
            queue.offer(nc);
            visited.add(nc);
          }
        }
      }
    }

    for (Character c : allLetters) {
      if (!visited.contains(c)) {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  public static Set<Character> findAllLetters(List<String> inputs, Map<Character, Integer> indegrees) {
    Set<Character> allLetters = new HashSet<>();
    for (String word : inputs) {
      for (char c : word.toCharArray()) {
        allLetters.add(c);
        if (!indegrees.containsKey(c)) {
          indegrees.put(c, 0);
        }
      }
    }
//    System.out.println("allLetters: " + allLetters);
    return allLetters;
  }

  //         Map<Character, Set<Character>> graph = buildGraph(pairs, indegrees);
  public static Map<Character, Set<Character>> buildGraph(
      List<String> inputs, Map<Character, Integer> indegrees) {
    Map<Character, Set<Character>> graph = new HashMap<>();
    Set<String> visited = new HashSet<>();
    int n = inputs.size();
    for (int i = 0; i < n - 1; i++) {
      String current = inputs.get(i);
      String next = inputs.get(i + 1);
      visited.add(current);

      if (current.startsWith(next) && current.length() > next.length()) {
        return Collections.emptyMap();
      }

      if (current.equals(next)) {
        continue;
      }

      if (visited.contains(next)) {
        return Collections.emptyMap();
      }

      int minLength = Math.min(current.length(), next.length());
      for (int j = 0; j < minLength; j++) {
        char cc = current.charAt(j);
        char nc = next.charAt(j);

        if (cc != nc) {
          if (!graph.containsKey(cc)) {
            graph.put(cc, new HashSet<>());
          }
          graph.get(cc).add(nc);
          indegrees.put(nc, indegrees.get(nc) + 1);
          break;
        }
      }
    }
    return graph;
  }
}
