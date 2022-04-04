package oa.az.min_score;

import java.util.*;

public class MinScore {

  public static int getMinScore(
      int productsNodes, List<Integer> productsFrom, List<Integer> productsTo) {
    Map<Integer, Set<Integer>> product2RelatedProducts =
        crateProductsRelationship(productsFrom, productsTo);

    Set<List<Integer>> trios = findTrios(product2RelatedProducts);
    if (trios.size() == 0) {
      return -1;
    }

    System.out.println("trios:" + trios);

    int min = Integer.MAX_VALUE;
    for (List<Integer> trio : trios) {
      min = Math.min(min, getScore(product2RelatedProducts, trio));
    }

    return min;
  }

  private static int getScore(Map<Integer, Set<Integer>> p2rp, List<Integer> trio) {
    int score = 0;

    for (int product : trio) {
      score += p2rp.get(product).size();
    }

    return score - 6;
  }

  private static Set<List<Integer>> findTrios(Map<Integer, Set<Integer>> product2RelatedProducts) {
    Set<List<Integer>> trios = new HashSet<>();
    for (Map.Entry<Integer, Set<Integer>> e : product2RelatedProducts.entrySet()) {
      int p = e.getKey();
      Set<Integer> rpSet = e.getValue();
      for (int rp : rpSet) {
        Set<Integer> secondRpSet = product2RelatedProducts.get(rp);
        for (int srp : secondRpSet) {
          // found trio
          if (rpSet.contains(srp)) {
            List<Integer> trio = Arrays.asList(p, rp, srp);
            Collections.sort(trio);
            trios.add(trio);
          }
        }
      }
    }
    return trios;
  }

  private static Map<Integer, Set<Integer>> crateProductsRelationship(
      List<Integer> productsFrom, List<Integer> productsTo) {
    Map<Integer, Set<Integer>> p2rp = new HashMap<>();
    for (int i = 0; i < productsFrom.size(); i++) {
      int productFrom = productsFrom.get(i);
      int productTo = productsTo.get(i);

      Set<Integer> ptList = p2rp.getOrDefault(productFrom, new HashSet<>());
      ptList.add(productTo);
      p2rp.put(productFrom, ptList);

      Set<Integer> pfList = p2rp.getOrDefault(productTo, new HashSet<>());
      pfList.add(productFrom);
      p2rp.put(productTo, pfList);
    }

    System.out.println("product2RelatedProducts: " + p2rp);
    return p2rp;
  }

  public static void main(String[] args) {
    System.out.println(
        getMinScore(6, Arrays.asList(1, 2, 2, 3, 4, 5), Arrays.asList(2, 4, 5, 5, 5, 6)));
    System.out.println(
        getMinScore(5, Arrays.asList(1, 1, 2, 2, 3, 4), Arrays.asList(2, 3, 3, 4, 4, 5)));
    System.out.println(getMinScore(7, Arrays.asList(2, 3, 5, 1), Arrays.asList(1, 6, 1, 7)));
  }
}
