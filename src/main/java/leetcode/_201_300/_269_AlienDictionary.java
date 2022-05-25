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
    System.out.println(alienOrder(Arrays.asList("a", "b", "c")));
    System.out.println(alienOrder(Arrays.asList("uber", "car", "cap")));
    System.out.println(alienOrder(Arrays.asList("uber", "car", "cap", "car")));
    System.out.println(alienOrder(Arrays.asList("wrt", "wrf", "er", "ett", "rftt")));
    System.out.println(alienOrder(Arrays.asList("z", "x")));
    System.out.println(alienOrder(Arrays.asList("z", "x", "z")));
    System.out.println(alienOrder(Arrays.asList("z", "z")));
    System.out.println(alienOrder(Arrays.asList("z", "z", "z")));
    System.out.println(alienOrder(Arrays.asList("wrt", "wrtkj")));
  }

  public static String alienOrder(List<String> inputs) {
    Map<Character, Character> pairs = findPairs(inputs);
    if (pairs.isEmpty()) {
      return "";
    }

    Set<Character> allLetters = findAllLetters(inputs);
    Map<Character, Integer> indegrees = new HashMap<>();
    Map<Character, Set<Character>> graph = buildGraph(pairs, indegrees);
//    System.out.println("indegrees: " + indegrees);


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

  public static Map<Character, Character> findPairs(List<String> inputs) {
    Map<Character, Character> pairs = new HashMap<>();
    Set<String> visited = new HashSet<>();
    int n = inputs.size();
    for (int i = 0; i < n - 1; i++) {
      String current = inputs.get(i);
      visited.add(current);
      String next = inputs.get(i + 1);

      if (current.equals(next)) {
        pairs.put(current.charAt(0), current.charAt(0));
        continue;
      }

      if (visited.contains(next)) {
        return new HashMap<>();
      }
      // uber uber, ub ube
      int p = 0, q = 0;
      while (p < current.length() && q < next.length()) {
        char cc = current.charAt(p);
        char nc = next.charAt(q);
        if (cc == nc) {
          p++;
          q++;
          continue;
        }
        pairs.put(cc, nc);
        break;
      }

      if (q < next.length() - 1) {
        pairs.put(next.charAt(q), next.charAt(q));
      }
    }
//    System.out.println("pairs: " + pairs);

    return pairs;
  }

  public static Set<Character> findAllLetters(List<String> inputs) {
    Set<Character> allLetters = new HashSet<>();
    for (String word : inputs) {
      for (char c : word.toCharArray()) {
        allLetters.add(c);
      }
    }
//    System.out.println("allLetters: " + allLetters);
    return allLetters;
  }

  //         Map<Character, Set<Character>> graph = buildGraph(pairs, indegrees);
  public static Map<Character, Set<Character>> buildGraph(
      Map<Character, Character> pairs, Map<Character, Integer> indegrees) {
    Map<Character, Set<Character>> graph = new HashMap<>();
    for (Map.Entry<Character, Character> e : pairs.entrySet()) {
      char fromChar = e.getKey();
      char toChar = e.getValue();

      if (!graph.containsKey(fromChar)) {
        graph.put(fromChar, new HashSet<>());
        indegrees.putIfAbsent(fromChar, 0);
      }
      graph.get(fromChar).add(toChar);

      if (!indegrees.containsKey(toChar)) {
        indegrees.put(toChar, 0);
      }
      indegrees.put(toChar, indegrees.get(toChar) + 1);
    }
//    System.out.println("graph: " + graph);

    return graph;
  }
}
