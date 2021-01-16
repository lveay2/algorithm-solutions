package lintcode.twopointers;

public class _32_Minimum_Window_Substring {

    private static int[] letters = new int[256];

    public static String minWindow(String source, String target) {
        String result = "";
        if (source == null || source.length() == 0) {
            return result;
        }

        for (int i = 0; i < target.length(); i++) {
            letters[target.charAt(i)]++;
        }

        int min = Integer.MAX_VALUE;
        int end = 0;
        int n = source.length();
        for (int start = 0; start < n; start++) {
            while (end < n && !isAllFound()) {
                letters[source.charAt(end)]--;
                end++;
            }

            if (isAllFound() && end - start < min) {
                min = end - start;
                result = source.substring(start, end);
            }

            letters[source.charAt(start)]++;
        }

        return result;
    }

    private static boolean isAllFound() {
        for (int i : letters) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("abc", "ac"));
        letters = new int[256];
        System.out.println(minWindow("adobecodebanc", "abc"));
        letters = new int[256];
        System.out.println(minWindow("abc", "aa"));
        letters = new int[256];
        System.out.println(minWindow("adfqeradboaf23098724huhfda923hadf78adfhadfhadfaodiyfas8", "dfje89affefy8f"));
    }

}
