package lintcode.system_design.tiny_url;

import java.util.HashMap;
import java.util.Map;

public class TinyUrlII {

    private Map<String, String> s2l = new HashMap<>();
    private Map<String, String> l2s = new HashMap<>();
    private final String charset = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String URL_PREFIX = "http://tiny.url/";
    private int count = 0;

    public String createCustom(String long_url, String key) {
        String short_url = URL_PREFIX + key;
        if (l2s.containsKey(long_url)) {
            if (l2s.get(long_url).equals(short_url)) {
                return short_url;
            } else {
                return "error";
            }
        }

        if (s2l.containsKey(short_url)) {
            return "error";
        }

        l2s.put(long_url, short_url);
        s2l.put(short_url, long_url);

        return short_url;
    }

    /*
     * @param long_url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String long_url) {
        if (l2s.containsKey(long_url)) {
            return l2s.get(long_url);
        }

        String short_url = getNewUrl();
        l2s.put(long_url, short_url);
        s2l.put(short_url, long_url);

        return short_url;
    }

    private String getNewUrl() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = count; i < 6; i++, j /= 62) {
            sb.append(charset.charAt(j % 62));
        }
        count++;
        return URL_PREFIX + sb.toString();
    }

    /*
     * @param short_url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String short_url) {
        return s2l.getOrDefault(short_url, "error");
    }

    public static void main(String[] args) {
        TinyUrlII tinyUrlII = new TinyUrlII();
        System.out.println(tinyUrlII.createCustom("http://www.lintcode.com/", "lccode"));
        System.out.println(tinyUrlII.longToShort("http://www.lintcode.com/problem/"));
        System.out.println(tinyUrlII.shortToLong("http://tiny.url/lccode"));
        System.out.println(tinyUrlII.createCustom("http://www.lintcode.com/", "lc"));
        System.out.println(tinyUrlII.createCustom("http://www.lintcode.com/en/ladder/", "lccode"));
    }

}
