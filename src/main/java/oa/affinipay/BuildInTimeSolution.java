package oa.affinipay;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BuildInTimeSolution {

  private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("h:mm a");

  public static void main(String[] args) {
    BuildInTimeSolution bits = new BuildInTimeSolution();
    System.out.println(bits.addMinutes("9:13 AM", 200));
    System.out.println(bits.addMinutes("09:13 AM", 200) + "\n");

    System.out.println(bits.addMinutes("7:24 PM", 100));
    System.out.println(bits.addMinutes("7:24 PM", 200) + "\n");

    System.out.println(bits.addMinutes("0:00 PM", 2));
    System.out.println(bits.addMinutes("00:00 PM", 2));
    System.out.println(bits.addMinutes("0:00 PM", -2));
    System.out.println(bits.addMinutes("00:00 PM", -2) + "\n");

    System.out.println(bits.addMinutes("12:00 AM", 2));
    System.out.println(bits.addMinutes("12:00 AM", -2));
    System.out.println(bits.addMinutes("12:00 PM", 2));
    System.out.println(bits.addMinutes("12:00 PM", -2) + "\n");

    System.out.println(bits.addMinutes("12:59 AM", 2));
    System.out.println(bits.addMinutes("12:59 AM", -2));
    System.out.println(bits.addMinutes("12:59 PM", 2));
    System.out.println(bits.addMinutes("12:59 PM", -2) + "\n");

    System.out.println(bits.addMinutes("11:59 AM", 2));
    System.out.println(bits.addMinutes("11:59 PM", 2) + "\n");

    System.out.println(bits.addMinutes("11:59 PM", Integer.MAX_VALUE));
    System.out.println(bits.addMinutes("11:59 PM", Integer.MIN_VALUE) + "\n");
    System.out.println(bits.addMinutes("", 2));
  }

  public String addMinutes(String time, int minutes) {
    LocalTime localTime;
    try {
      localTime = LocalTime.parse(time, df);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(
          "time: ["
              + time
              + "], minutes: ["
              + minutes
              + "] cause DateTimeParseException: "
              + e.getMessage());
    }

    LocalTime newLocalTime = localTime.plusMinutes(minutes);
    return df.format(newLocalTime);
  }
}
