package oa.az.coin_collection;

public class CoinCollection {

  private static final char HEAD_COIN = 'H';
  private static final char TAIL_COIN = 'T';

  private static int minimumCoinFlips(String coins) {
    int result = 0;
    int firstTailCoinIndex = -1;
    int firstHeadCoinIndex = -1;
    for (int i = 0; i < coins.length(); i++) {
      if (coins.charAt(i) == TAIL_COIN && firstHeadCoinIndex == -1) {
        result += 1;
        continue;
      }

      if (coins.charAt(i) == HEAD_COIN && firstHeadCoinIndex == -1) {
        firstHeadCoinIndex = i;
        continue;
      }

      if (coins.charAt(i) == HEAD_COIN && firstTailCoinIndex == -1 && firstHeadCoinIndex != -1) {
        continue;
      }

      if (coins.charAt(i) == TAIL_COIN && firstTailCoinIndex == -1) {
        firstTailCoinIndex = i;
        continue;
      }

      if (coins.charAt(i) == HEAD_COIN && firstTailCoinIndex != -1) {
        result += 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println("HHHH: 0 == " + minimumCoinFlips("HHHH"));
    System.out.println("HHTHT: 1 == " + minimumCoinFlips("HHTHT"));
    System.out.println("HHTHTTTTT: 1 == " + minimumCoinFlips("HHTHTTTTT"));
    System.out.println("HHTHTHTTTTT: 2 == " + minimumCoinFlips("HHTHTHTTTTT"));
    System.out.println("THHTHT: 2 == " + minimumCoinFlips("THHTHT"));
    System.out.println("TTTHHTHT: 4 == " + minimumCoinFlips("TTTHHTHT"));

    System.out.println("THHTTTH: 2 == " + minimumCoinFlips("THHTTTH"));
    System.out.println("HHHTHTH: 2 == " + minimumCoinFlips("HHHTHTH"));
    System.out.println("HHHHTT: 0 == " + minimumCoinFlips("HHHHTT"));
    System.out.println("H: 0 == " + minimumCoinFlips("H"));
    System.out.println("T: 1 == " + minimumCoinFlips("T"));
  }
}
