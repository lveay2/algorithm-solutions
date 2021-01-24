package leetcode._901_1000;

import javafx.util.Pair;

import java.util.*;

public class _981_TimeBasedKeyValueStore {

    static class TimeMap {

        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }

            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return null;
            }

            TreeMap<Integer, String> temp = map.get(key);
            return temp == null ? null : temp.floorEntry(timestamp).getValue();
        }

    }

    static class TimeMap2 {

        Map<String, List<Pair<Integer, String>>> M;

        TimeMap2() {
            M = new HashMap();
        }

        public void set(String key, String value, int timestamp) {
            if (!M.containsKey(key)) {
                M.put(key, new ArrayList<Pair<Integer, String>>());
            }

            M.get(key).add(new Pair(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!M.containsKey(key)) {
                return "";
            }

            List<Pair<Integer, String>> A = M.get(key);
            int i = Collections.binarySearch(A, new Pair<>(timestamp, ""),
                    Comparator.comparingInt(Pair::getKey));

            if (i >= 0) {
                return A.get(i).getValue();
            } else if (i == -1) {
                return "";
            } else {
                return A.get(-i - 2).getValue();
            }
        }

        public String get2(String key, int timestamp) {
            if (!M.containsKey(key)) {
                return null;
            }

            List<Pair<Integer, String>> list = M.get(key);
            return binarySearch(list, timestamp);
        }

        private String binarySearch(List<Pair<Integer, String>> list, int timestamp) {
            int left = 0, right = list.size() - 1;

            while (left + 1 < right) {
                int mid = left + (right - left) / 2;

                if (list.get(mid).getKey() == timestamp) {
                    return list.get(mid).getValue();
                } else if (list.get(mid).getKey() < timestamp) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (list.get(right).getKey() <= timestamp) {
                return list.get(right).getValue();
            }

            if (list.get(left).getKey() <= timestamp) {
                return list.get(left).getValue();
            }

            return null;
        }

    }

    public static void main(String[] args) {
        TimeMap map = new TimeMap();
        map.set("k1", "v1", 1);
        System.out.println(map.get("k1", 1));
        System.out.println(map.get("k1", 3));
        map.set("k1", "v2", 4);
        System.out.println(map.get("k1", 4));
        System.out.println(map.get("k1", 5));

        TimeMap2 map2 = new TimeMap2();
        map2.set("k1", "v1", 1);
        System.out.println(map2.get("k1", 1));
        System.out.println(map2.get("k1", 3));
        map2.set("k1", "v2", 4);
        System.out.println(map2.get("k1", 4));
        System.out.println(map2.get("k1", 5));

        map2 = new TimeMap2();
        map2.set("k1", "v1", 1);
        System.out.println(map2.get2("k1", 1));
        System.out.println(map2.get2("k1", 3));
        map2.set("k1", "v2", 4);
        System.out.println(map2.get2("k1", 4));
        System.out.println(map2.get2("k1", 5));

    }

}
