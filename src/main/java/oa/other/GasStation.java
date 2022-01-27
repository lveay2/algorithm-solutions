package oa.other;

import java.util.Arrays;

public class GasStation {

    private static String calculate(String[] array) {
        int size = Integer.valueOf(array[0]);

        int[] gasArr = readArray(array, 0);
        int[] costArr = readArray(array, 2);

        System.out.println(Arrays.toString(gasArr));
        System.out.println(Arrays.toString(costArr));

        for (int i = 1; i <= size; i++) {

        }


        return "impossible";
    }

    private static int[] readArray(String[] array, int i) {
        int[] res = new int[array.length - 1];

        int index = 0;
        for (String str : array) {
            res[index++] = Integer.valueOf(str.split(":")[i]);
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(GasStation.calculate(new String[]{"4", "3:1", "2:2", "1:2", "0:1"}));
    }

}
