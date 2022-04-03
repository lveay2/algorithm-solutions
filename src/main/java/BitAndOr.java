public class BitAndOr {

    public static void main(String[] args) {
        System.out.println('a');
        // 利用或操作 | 和空格将英文字符转换为小写
        System.out.println(Character.toString('a' | ' '));
        System.out.println(Character.toString('A' | ' '));

        // 利用与操作 & 和下划线将英文字符转换为大写
        System.out.println(Character.toString('b' & '_'));
        System.out.println(Character.toString('B' & '_'));

        // 利用异或操作 ^ 和空格进行英文字符大小写互换
        System.out.println(Character.toString('d' ^ ' '));
        System.out.println(Character.toString('D' ^ ' '));

        // 判断两个数是否异号
        int x = -1, y = 2;
        System.out.println(((x ^ y) < 0)); // true

        x = 3;
        y = 2;
        System.out.println(((x ^ y) < 0)); // false

        // 不用临时变量交换两个数
        int a = 1, b = 2;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a: " + a + " b: " + b);
        // 现在 a = 2, b = 1

        // 加一
        int n = 1;
        n = -~n;
        System.out.println("n: " + n);
        // 现在 n = 2

        // 减一
        n = 2;
        n = ~-n;
        System.out.println("n: " + n);
        // 现在 n = 1


    }
}
