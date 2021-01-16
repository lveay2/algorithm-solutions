package lintcode.system_design;

public class _128_Hash_Function {

    public static int hashCode(char[] key, int HASH_SIZE) {
        // write your code here
        long result = 0;
        for (char c : key) {
            result = (result * 33 + c) % HASH_SIZE;
        }

        return (int) result;
    }

    public static int hashCode2(char[] key, int HASH_SIZE) {
        long result = 0;

        for (char c : key) {
            result = result * 33 + c;
        }

        return (int) (result % HASH_SIZE);
    }


    public static void main(String[] args) {
        System.out.println("78 == " + hashCode("abcd".toCharArray(), 100));
        System.out.println("978 == " + hashCode("abcd".toCharArray(), 1000));
        System.out.println("549 == " + hashCode("ubuntu".toCharArray(), 1007));
        System.out.println("1673 == " + hashCode("abcdefghijklmnopqrstuvwxyz".toCharArray(), 2607));
        System.out.println("78 == " + hashCode2("abcd".toCharArray(), 100));
        System.out.println("978 == " + hashCode2("abcd".toCharArray(), 1000));
        System.out.println("549 == " + hashCode2("ubuntu".toCharArray(), 1007));
        // https://www.cnblogs.com/wangzhongqiu/p/11121957.html
        System.out.println("1673 == " + hashCode2("abcdefghijklmnopqrstuvwxyz".toCharArray(), 2607));
    }

}
