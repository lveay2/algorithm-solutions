package oa.black_rock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectBid {

  public static void main(String[] args) {
    ProjectBid solution = new ProjectBid();
    System.out.println(solution.minCost(2, Arrays.asList(2, 0, 1, 2), Arrays.asList(8, 7, 6, 9)));
    System.out.println(solution.minCost(3, Arrays.asList(2, 0, 1, 2), Arrays.asList(8, 7, 6, 9)));
  }

  public Long minCost(int n, List<Integer> projectIds, List<Integer> bids) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < projectIds.size(); i++) {
      int pid = projectIds.get(i);
      int bid = bids.get(i);

      if (!map.containsKey(pid)) {
        map.put(pid, bid);
      } else {
        if (bid < map.get(pid)) {
          map.put(pid, bid);
        }
      }
    }

    if (n != map.size()) {
      return -1L;
    }

    Long ans = 0L;
    for (Integer value : map.values()) {
      ans += value;
    }

    return ans;
  }
}
