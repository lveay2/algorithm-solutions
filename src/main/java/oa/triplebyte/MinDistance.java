package oa.triplebyte;

public class MinDistance {

    public static int deletion_distance(String str1, String str2) {
//    int dp[][]  = new int[str1.length()][str2.length()];
//    for (int i = 0; i <= str1.length(); i++) {
//      for (int j = 0; j <= str2.length(); j++) {
//        if (i == 0 || j == 0) {
//          dp[i][j] = 0;
//        } else {
//          dp[i][j] = str1.charAt(i - 1) == str2.charAt(j - 1) ? dp[i - 1][j - 1]  + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
//        }
//      }
//    }
//    int diff = dp[str1.length()][str2.length()];
//    return str1.length() - diff + str2.length() - diff;

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int val = dp[str1.length()][str2.length()];
        return str1.length() - val + str2.length() - val;
    }

    public static void main(String[] args) {
        System.out.println(deletion_distance("sea", "eat"));
    }

}
