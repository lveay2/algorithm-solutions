package oa.google;

import java.util.*;

/**
 * Given a string that represents a file, write a function that prints its directory structure. E.g.
 * "/app/components/header"
 *
 * <p>// * app // ** components // *** header
 *
 * <p>Follow up question: Input files = [ "/app/components/header", "/app/service",
 * "/app/components/components/header", "/app/tests/header", "/app/tests/header/fee",
 * "/images/images.png", "tsconfig.json", "index.html" ]
 *
 * <p>Output: * app ** components *** header ** service *** components **** header ** tests **** fee
 * * images ** images.png * tsconfig.json * index.html
 *
 * <p>Follow up question 2: use Node class
 */
public class DirectoryStructure {

  private static List<String> printSingleDirStructure(String input) {
    List<String> result = new ArrayList<>();

    String[] strs = input.split("/");
    String level = "";
    for (String s : strs) {
      if ("".equals(s)) {
        continue;
      }

      level += "*";
      result.add(level + " " + s);
    }

    return result;
  }

  private static List<String> printDirStructure(List<String> input) {
    List<String> result = new ArrayList<>();
    Set<String> set = new HashSet<>();

    for (String s : input) {
      List<String> dirs = printSingleDirStructure(s);

      for (String str : dirs) {
        if (set.contains(str)) {
          continue;
        }

        set.add(str);
        result.add(str);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(printSingleDirStructure("/app/components/header"));
    System.out.println(printSingleDirStructure("app/components/header"));
    System.out.println(
        printDirStructure(
            Arrays.asList(
                "/app/components/header",
                "/app/service",
                "/app/components/components/header",
                "/app/tests/header",
                "/app/tests/header/fee",
                "/images/images.png",
                "tsconfig.json",
                "index.html")));

    System.out.println(printSingleDirStructure2("/app/components/header"));
    System.out.println(printSingleDirStructure2("app/components/header"));
    System.out.println(
        printDirStructure2(
            Arrays.asList(
                "/app/components/header",
                "/app/service",
                "/app/components/components/header",
                "/app/tests/header",
                "/app/tests/header/fee",
                "/images/images.png",
                "tsconfig.json",
                "index.html")));
  }

  private static Node printDirStructure2(List<String> input) {
    Node root = Node.createNode("/", 0);
    Set<Node> rootChildren = root.children;

    Map<Node, Set<Node>> map = new HashMap<>(32);
    for (String s : input) {
      List<Node> nodes = printSingleDirStructure2(s);

      for (Node node : nodes) {
        if (node.level == 1) {
          rootChildren.add(node);
        }

        if (!map.containsKey(node)) {
          map.put(node, node.children);
        }

        map.get(node).addAll(node.children);
      }
    }

    for (Map.Entry<Node, Set<Node>> entry : map.entrySet()) {
      entry.getKey().children = entry.getValue();
    }

    return root;
  }

  private static List<Node> printSingleDirStructure2(String input) {
    List<Node> result = new ArrayList<>();

    String[] strs = input.split("/");
    String star = "";
    int level = 0;
    Node parentNode = null;
    for (String s : strs) {
      if ("".equals(s)) {
        continue;
      }

      level++;
      star += "*";
      Node currentNode = Node.createNode(star + " " + s, level);
      if (parentNode != null) {
        parentNode.children.add(currentNode);
      }

      result.add(currentNode);
      parentNode = currentNode;
    }

    return result;
  }

  static class Node {

    static Map<String, Map<Integer, Node>> map = new HashMap<>();

    String name;
    int level;
    Set<Node> children;

    private Node(String name, int level) {
      this.name = name;
      this.level = level;
      this.children = new HashSet<>();
    }

    public static Node createNode(String name, int level) {
      if (map.containsKey(name) && map.get(name).containsKey(level)) {
        return map.get(name).get(level);
      }

      Node newNode = new Node(name, level);
      if (!map.containsKey(name)) {
        map.put(name, new HashMap<>());
      }
      map.get(name).put(level, newNode);

      return newNode;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node node = (Node) o;
      return level == node.level && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, level);
    }

    @Override
    public String toString() {
      return "Node{" + "name='" + name + '\'' + ", level=" + level + ", children=" + children + '}';
    }
  }
}
