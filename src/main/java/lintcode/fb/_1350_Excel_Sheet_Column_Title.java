package lintcode.fb;

public class _1350_Excel_Sheet_Column_Title {

  public static String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();

    while (n > 0) {
      n--;
      sb.append((char) (n % 26 + 'A'));
      n /= 26;
    }

    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.out.println(convertToTitle(10));
  }
}
