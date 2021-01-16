package lintcode.system_design;

import java.util.HashMap;
import java.util.Map;

public class Memcache {
    class Cache {
        int value;
        int expired;

        Cache(int value, int expired) {
            this.value = value;
            this.expired = expired;
        }

    }

    Map<Integer, Cache> map;
    int FOREVER = -1;

    public Memcache() {
        map = new HashMap<>();
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: An integer
     */
    public int get(int curtTime, int key) {
        if (map.containsKey(key)) {
            Cache cache = map.get(key);

            if (curtTime <= cache.expired || cache.expired == FOREVER) {
                return cache.value;
            }
        }

        return Integer.MAX_VALUE;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param value: An integer
     * @param ttl: An integer
     * @return: nothing
     */
    public void set(int curtTime, int key, int value, int ttl) {
        int expired = FOREVER;
        if (ttl != 0) {
            expired = curtTime + ttl - 1;
        }
        Cache cache = new Cache(value, expired);
        map.put(key, cache);
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: nothing
     */
    public void delete(int curtTime, int key) {
        map.remove(key);
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int incr(int curtTime, int key, int delta) {
        if (map.containsKey(key)) {
            Cache cache = map.get(key);

            if (curtTime <= cache.expired || cache.expired == FOREVER) {
                cache.value += delta;
                return cache.value;
            }
        }

        return Integer.MAX_VALUE;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int decr(int curtTime, int key, int delta) {
        if (map.containsKey(key)) {
            Cache cache = map.get(key);

            if (curtTime <= cache.expired || cache.expired == FOREVER) {
                cache.value -= delta;
                return cache.value;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        Memcache m = new Memcache();
        System.out.println("2147483647 == " + m.get(1, 0));
        m.set(2, 1, 1, 2);
        System.out.println("1 == " + m.get(3, 1));
        System.out.println("2147483647 == " + m.get(4, 1));
        System.out.println("2147483647 == " + m.incr(5, 1, 1));
        m.set(6, 1, 3, 0);
        System.out.println("4 == " + m.incr(7, 1, 1));
        System.out.println("3 == " + m.decr(8, 1, 1));
        System.out.println("3 == " + m.get(9, 1));
        m.delete(10, 1);
        System.out.println("2147483647 == " + m.get(11, 1));
        System.out.println("2147483647 == " + m.incr(12, 1, 1));
    }

}