package leetcode._1_100;

public class _14_Longest_Common_Prefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }

        int mid = left + (right - left) / 2;
        String leftCommStr = longestCommonPrefix(strs, left, mid);
        String rightCommStr = longestCommonPrefix(strs, mid + 1, right);

        return commonPrefix(leftCommStr, rightCommStr);
    }

    private static String commonPrefix(String leftCommon, String rightCommon) {
        while (rightCommon.indexOf(leftCommon) != 0) {
            leftCommon = leftCommon.substring(0, leftCommon.length() - 1);
            if (leftCommon.equals("")) {
                return "";
            }
        }

        return leftCommon;
    }

    public static void main(String[] args) {
        System.out.println("fl == " + longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(" == " + longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

}
