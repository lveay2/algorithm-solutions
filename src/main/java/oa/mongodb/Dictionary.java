package oa.mongodb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aby2
 */
public class Dictionary {

  Map<String, Node> map;
  int size;
  int count;
  Node head;
  Node tail;
  Dictionary(int size) {
    this.map = new HashMap<>();
    head = new Node(null, null);
    tail = new Node(null, null);
    head.next = tail;
    head.prev = null;
    tail.next = null;
    tail.prev = head;
    this.size = size;
    this.count = 0;
  }

  public static void main(String[] args) {
    Dictionary s = new Dictionary(100);

    s.add("1", "1");
    s.add("2", "2");
    s.add("3", "3");
    System.out.println(s.fetch("3"));
    System.out.println(Arrays.deepToString(s.dump()));
    s.remove("3");
    System.out.println(s.fetch("3"));
    System.out.println(Arrays.deepToString(s.dump()));
  }

  public void add(String key, String value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      moveToEnd(node);
    } else {
      Node node = new Node(key, value);
      addNode(node);
      map.put(key, node);

      // check size
      count++;

      if (count == size) {
        Node remove = head.next;
        removeNode(remove);
        map.remove(remove.key);
      }
    }
  }

  private void moveToEnd(Node node) {
    removeNode(node);
    addNode(node);
  }

  private void addNode(Node node) {
    Node temp = tail.prev;
    temp.next = node;
    tail.prev = node;
    node.prev = temp;
    node.next = tail;

    //        node.prev = tail.prev;
    //        node.next = tail;
    //        tail.prev = node;
    //        tail.prev.next = node;
  }

  private void removeNode(Node node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }

  public String fetch(String key) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      return node.value;
    }

    return null;
  }

  public String remove(String key) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      removeNode(node);
      map.remove(key);
      return node.value;
    }

    return null;
  }

  // oldest -> newest
  public String[][] dump() {
    String[][] result = new String[size][2];

    Node current = head.next;
    int i = 0;
    while (current != null) {
      result[i][0] = current.key;
      result[i][1] = current.value;

      current = current.next;
      i++;
    }

    return result;
  }

  class Node {

    String key;
    String value;
    Node prev;
    Node next;

    Node(String key, String value) {
      this(key, value, null, null);
    }

    Node(String key, String value, Node prev, Node next) {
      this.key = key;
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }
}
