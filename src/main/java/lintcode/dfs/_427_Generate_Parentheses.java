package lintcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class _427_Generate_Parentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        List<String> seqs = new ArrayList<>();

        dfs(n, seqs, 0, result);

        return result;
    }

    private static void dfs(int n, List<String> seqs, int leftP, List<String> result) {
        if (seqs.size() == 2 * n) {
            String temp = String.join("", seqs);
            result.add(temp);
            return;
        }

        if (leftP < n) {
            seqs.add("(");
            dfs(n, seqs, leftP + 1, result);
            seqs.remove(seqs.size() - 1);
        }

        if (leftP > seqs.size() - leftP) {
            seqs.add(")");
            dfs(n, seqs, leftP, result);
            seqs.remove(seqs.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(0));
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(2));
        System.out.println(generateParenthesis(3));
    }

}
