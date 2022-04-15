package oa.karat;

import java.util.*;

/*
We have some clickstream data that we gathered on our client's website.
Using cookies, we collected snippets of users' anonymized URL histories
while they browsed the site. The histories are in chronological order,
and no URL was visited more than once per person.

Write a function that takes two users' browsing histories as input and
returns the longest contiguous sequence of URLs that appears in both.

Sample input:

user0 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
user1 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
user2 = ["a", "/one", "/two"]
user3 = ["/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"]
user4 = ["/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"]
user5 = ["a"]
user6 = ["/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"]

Sample output:
findContiguousHistory(user0, user1) => ["/pink", "/register", "/orange"]
findContiguousHistory(user0, user2) => [] (empty)
findContiguousHistory(user0, user0) => ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
findContiguousHistory(user2, user1) => ["a"]
findContiguousHistory(user5, user2) => ["a"]
findContiguousHistory(user3, user4) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user4, user3) => ["/plum", "/blue", "/tan", "/red"]
findContiguousHistory(user3, user6) => ["/tan", "/red", "/amber"]

n: length of the first user's browsing history
m: length of the second user's browsing history
*/
public class ClickStream {

  // time: O(n * c)
  // space: O(n)
  public static void main(String[] argv) {
    String[] user0 = {
      "/start", "/green", "/blue", new String("/pink"), "/register", "/orange", "/one/two"
    };
    String[] user1 = {"/start", "/pink", "/register", "/orange", "/red", "a"};
    String[] user2 = {"a", "/one", "/two"};
    String[] user3 = {
      "/pink",
      "/orange",
      "/yellow",
      "/plum",
      "/blue",
      "/tan",
      "/red",
      "/amber",
      "/HotRodPink",
      "/CornflowerBlue",
      "/LightGoldenRodYellow",
      "/BritishRacingGreen"
    };
    String[] user4 = {
      "/pink",
      "/orange",
      "/amber",
      "/BritishRacingGreen",
      "/plum",
      "/blue",
      "/tan",
      "/red",
      "/lavender",
      "/HotRodPink",
      "/CornflowerBlue",
      "/LightGoldenRodYellow"
    };
    String[] user5 = {"a"};
    String[] user6 = {"/pink", "/orange", "/six", "/plum", "/seven", "/tan", "/red", "/amber"};

    System.out.println(findContiguousHistory(user0, user1)); // => [/pink, /register, /orange]
    System.out.println(findContiguousHistory(user0, user2)); // => []

    System.out.println(
        findContiguousHistory(
            user0,
            user0)); // ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
    System.out.println(findContiguousHistory(user2, user1)); // => ["a"]
    System.out.println(findContiguousHistory(user5, user2)); // => ["a"]
    System.out.println(
        findContiguousHistory(user3, user4)); // => ["/plum", "/blue", "/tan", "/red"]
    System.out.println(
        findContiguousHistory(user4, user3)); // => ["/plum", "/blue", "/tan", "/red"]
    System.out.println(findContiguousHistory(user3, user6)); // => ["/tan", "/red", "/amber"]
  }

  private static List<String> findContiguousHistory(String[] a, String[] b) {
    List<String> result = new ArrayList<>();

    Map<String, Integer> b2Index = new HashMap<>();
    for (int i = 0; i < b.length; i++) {
      b2Index.put(b[i], i);
    }

    int m = a.length;
    int n = b.length;

    int max = 0;
    for (int i = 0; i < a.length; i++) {
      String url = a[i];
      if (!b2Index.containsKey(url)) {
        continue;
      }

      int p = i;
      int q = b2Index.get(url);
      int count = 1;
      List<String> temp = new ArrayList<>();
      temp.add(url);
      while (p + 1 < m && q + 1 < n) {
        p++;
        q++;
        if (a[p] != b[q]) {
          if (max <= count) {
            result = temp;
            max = count;
          }
          break;
        }
        temp.add(a[p]);
        count++;
      }
      if (max <= count) {
        result = temp;
        max = count;
      }
    }

    return result;
  }
}
