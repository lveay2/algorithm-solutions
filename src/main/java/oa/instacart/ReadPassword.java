package oa.instacart;

import java.util.Arrays;
import java.util.List;

public class ReadPassword {
  public static void main(String[] args) {
    int[] position = new int[] {2, 4};
    char[][] m = buildMatrix(Arrays.asList("S3KDA4", "4ASDSD", "ACEEDS", "ASDEED", "RTRYYU"));
    System.out.println(readSingleCharacter(m, position[1], position[0]));

    int[] position1 = new int[] {0, 0};
    char[][] m2 = buildMatrix(Arrays.asList("I3KDA4", "XTRYYU"));
    System.out.println(readSingleCharacter(m2, position1[1], position1[0]));
  }

  private static char[][] buildMatrix(List<String> l) {
    int n = l.size();
    char[][] m = new char[n][l.get(0).length()];
    for(int i = n - 1; i >= 0 ; i--) {
      m[n - 1 - i] = l.get(i).toCharArray();
    }
    System.out.println(Arrays.deepToString(m));
    return m;
  }

  private static char readSingleCharacter(char[][] matrix, int row, int col) {
    return matrix[row][col];
  }
}
