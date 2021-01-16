package oa.black_rock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ProductOfTheMaximumMinimumInADataset {

    public static List<Long> maxMin(List<String> operations, List<Integer> x) {
        List<Long> result = new ArrayList<>();

        TreeSet<Integer> set = new TreeSet<>();

        int size = operations.size();

        for (int i = 0; i < size; i++) {
            String operation = operations.get(i);
            int num = Integer.valueOf(x.get(i));
            if (operation.equals("pop")) {
                if (set.contains(num)) {
                    set.remove(num);
                }
            } else {
                set.add(num);
            }
            result.add((long) set.first() * set.last());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxMin(Arrays.asList("push", "push", "push", "pop"), Arrays.asList(1, 2, 3, 1)));
        System.out.println(maxMin(Arrays.asList("push", "push"), Arrays.asList(3, 2)));
    }

}
