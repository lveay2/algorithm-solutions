package lintcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class _652_Factorization {

  private final List<Integer> path = new ArrayList<>();
  private final List<List<Integer>> result = new ArrayList<>();

  public static void main(String[] args) {
    _652_Factorization solution = new _652_Factorization();
    System.out.println(solution.getFactors(8));
  }

  public List<List<Integer>> getFactors(int n) {
    dfs(2, n);
    return result;
  }

  private void dfs(int start, int remain) {
    if (remain == 1) {
      if (path.size() != 1) {
        result.add(new ArrayList<>(path));
      }
      return;
    }

    for (int i = start; i <= remain; i++) {
      if (i > remain / i) {
        break;
      }

      if (remain % i == 0) {
        path.add(i);
        dfs(i, remain / i);
        path.remove(path.size() - 1);
      }
    }

    path.add(remain);
    dfs(remain, 1);
    path.remove(path.size() - 1);
  }
}
