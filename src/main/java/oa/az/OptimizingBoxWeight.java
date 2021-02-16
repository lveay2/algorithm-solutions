package oa.az;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimizingBoxWeight {

    public static List<Integer> minimalHeaviestSetA(List<Integer> arr) {
        Collections.sort(arr);

        long sum = 0;
        for (int n : arr) {
            sum += n;
        }

        List<Integer> result = new ArrayList<>();
        long temp = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            temp += arr.get(i);
            result.add(0, arr.get(i));
            if (temp > sum / 2) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(OptimizingBoxWeight.minimalHeaviestSetA(Arrays.asList(5, 3, 2, 4, 1, 2)));
        System.out.println(OptimizingBoxWeight.minimalHeaviestSetA(Arrays.asList(4, 2, 5, 1, 6)));
        System.out.println(OptimizingBoxWeight.minimalHeaviestSetA(Arrays.asList(1, 2, 5, 8, 4)));
    }

}
