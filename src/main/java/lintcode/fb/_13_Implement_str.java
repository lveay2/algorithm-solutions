package lintcode.fb;

public class _13_Implement_str {

    public static int strStr(String source, String target) {
        if (source == null) {
            return -1;
        }

        if (source.equals(target)) {
            return 0;
        }

        String subSource = source;
        int i = 0;
        while (i < source.length()) {
            if (subSource.indexOf(target) != 0) {
                subSource = subSource.substring(1);
            } else {
                return i;
            }

            i++;
        }

        return -1;
    }


    public static void main(String[] args) {
        System.out.println(strStr("abcde", "e"));
        System.out.println(strStr("", ""));
    }

}
