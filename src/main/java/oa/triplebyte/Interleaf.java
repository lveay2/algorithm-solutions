package oa.triplebyte;

public class Interleaf {

  public static String interleaf(String str1, String str2) {
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();
    char[] arr = new char[arr1.length + arr2.length];

    int i = 0, j = 0, c = 0;

    while (i < str1.length() && j < str2.length()) {
      if (c % 2 == 0) {
        arr[c] = arr1[i];
        i++;
      } else {
        arr[c] = arr2[j];
        j++;
      }
      c++;
    }

    while (i < str1.length()) {
      arr[c++] = arr1[i++];
    }

    while (j < str2.length()) {
      arr[c++] = arr2[j++];
    }

    return new String(arr);
  }

  public static int nearest_power_of_two(int n) {
    int result = 0;
    for (int i = 1; i <= n; i++) {
      int temp = (int) Math.pow(2, i);
      if (temp > n) {
        break;
      }
      result = temp;
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(interleaf("", "ABCD"));
    System.out.println(interleaf("abcd", ""));
    System.out.println(interleaf("abcd", "ABCD"));
    System.out.println(interleaf("abcdeee", "ABCD"));
    System.out.println(interleaf("abcd", "ABCDEEE"));
  }
}
