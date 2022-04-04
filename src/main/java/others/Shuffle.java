package others;

import java.util.Arrays;
import java.util.Random;

public class Shuffle {

  private static int[] shuffle(int[] arr) {
    Random random = new Random();
    for (int i = arr.length - 1; i >= 0; i--) {
      int jj = random.nextInt(i + 1);
      swap(arr, i, jj);
    }
    return arr;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[j];
    arr[j] = arr[i];
    arr[i] = temp;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(shuffle(new int[] {1, 2, 3, 4, 5})));
  }
}
