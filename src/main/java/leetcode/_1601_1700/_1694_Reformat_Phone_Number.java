package leetcode._1601_1700;

public class _1694_Reformat_Phone_Number {

  public static String reformatNumber(String number) {
    number = number.replaceAll(" ", "").replaceAll("\\-", "");

    if (number.length() <= 3) {
      return number;
    }

    StringBuilder sb = new StringBuilder();
    int n = number.length();
    for (int i = 0; i < n; i += 3) {
      if (i > 0) {
        sb.append("-");
      }

      if (n - i == 4) {
        sb.append(number, i, i + 2);
        sb.append("-");
        i += 2;
        sb.append(number, i, i + 2);
      } else if (n - i == 2) {
        sb.append(number, i, i + 2);
      } else {
        sb.append(number, i, i + 3);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(reformatNumber("1-23-45 6"));
    System.out.println(reformatNumber("123 4-567"));
    System.out.println(reformatNumber("123 4-5678"));
  }
}
