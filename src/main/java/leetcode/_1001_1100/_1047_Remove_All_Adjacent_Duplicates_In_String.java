package leetcode._1001_1100;

public class _1047_Remove_All_Adjacent_Duplicates_In_String {

    public static String removeDuplicates(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        StringBuilder sb = new StringBuilder();

        int n = S.length();
        char[] arr = S.toCharArray();

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (k != 0 && arr[i] == sb.charAt(k - 1)) {
                sb.deleteCharAt(k - 1);
                k--;
            } else {
                sb.append(arr[i]);
                k++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));
    }

}
