package lintcode.system_design.tiny_url;

import java.util.HashMap;
import java.util.Random;

public class TinyUrl {

  private static final String URL_PREFIX = "http://tiny.url/";
  private final HashMap<String, String> short2Long = new HashMap<>();
  private final HashMap<String, String> long2Short = new HashMap<>();

  public static void main(String[] args) {
    TinyUrl tinyUrl = new TinyUrl();
    System.out.println(
        tinyUrl.shortToLong(tinyUrl.longToShort("http://www.lintcode.com/faq/?id=10")));
  }

  public String longToShort(String url) {
    if (long2Short.containsKey(url)) {
      return long2Short.get(url);
    }

    while (true) {
      String shortUrl = generateShortUrl();

      if (!short2Long.containsKey(shortUrl)) {
        short2Long.put(shortUrl, url);
        long2Short.put(url, shortUrl);
        return shortUrl;
      }
    }
  }

  private String generateShortUrl() {
    String allowedChars =
        "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Random r = new Random();
    String result = URL_PREFIX;
    for (int i = 0; i < 6; i++) {
      int index = r.nextInt(62);
      result += allowedChars.charAt(index);
    }
    return result;
  }

  /*
   * @param url: a short url starts with http://tiny.url/
   * @return: a long url
   */
  public String shortToLong(String url) {
    if (short2Long.containsKey(url)) {
      return short2Long.get(url);
    }

    return null;
  }
}
