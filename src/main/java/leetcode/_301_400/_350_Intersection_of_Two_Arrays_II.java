package leetcode._301_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _350_Intersection_of_Two_Arrays_II {

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> n2c = new HashMap<>();
        for (int j : nums1) {
            n2c.put(j, n2c.getOrDefault(j, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (n2c.containsKey(nums2[i])) {
                result.add(nums2[i]);
                n2c.put(nums2[i], n2c.get(nums2[i]) - 1);
                if (n2c.get(nums2[i]) == 0) {
                    n2c.remove(nums2[i]);
                }
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {

    }

}
