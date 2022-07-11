package oa.az.q;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PacketChannel {

  public static void main(String[] args) {
    System.out.println(maximumQuality(Arrays.asList(1, 2, 3, 4, 5), 2));
    System.out.println("----");
    System.out.println(maximumQuality(Arrays.asList(5, 2, 2, 1, 5, 3), 2));
  }

  private static long maximumQuality(List<Integer> packets, int channels) {
    int pSize = packets.size();
    long ans = 0;
    if (pSize == channels) {
      for (int p : packets) {
        ans += p;
      }
      return ans;
    }

    Collections.sort(packets);
    for (int i = 1; i <= channels - 1; i++) {
      ans += packets.get(pSize - i);
    }

    int remain = pSize - channels + 1;
    if (remain % 2 == 0) {
      System.out.println(
          ans + " " + packets.get((remain - 1) / 2) + " " + packets.get(((remain - 1) / 2) + 1));

      double doubleValue =
          Math.ceil(
              (double) (packets.get((remain - 1) / 2) + packets.get(((remain - 1) / 2) + 1)) / 2);
      System.out.println(doubleValue);

      ans += Double.valueOf(doubleValue).longValue();
    } else {
      System.out.println(ans + " " + packets.get((remain - 1) / 2));

      ans += packets.get(remain / 2);
    }

    return ans;
  }
}
