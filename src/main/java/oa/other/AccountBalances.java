package oa.other;

import java.util.*;

public class AccountBalances {

  public static void main(String[] args) {
    int[] balances = {1000, 1500};
    String[] requests = {
      "withdraw 1613327630 2 480",
      "withdraw 1613327644 2 800",
      "withdraw 1614105244 1 100",
      "deposit 1614108844 2 200",
      "withdraw 1614108845 2 150",
    };

    System.out.println(Arrays.toString(calculateBalances(balances, requests)));
    System.out.println("-----------------");

    int[] balances2 = {20, 1000, 500, 40, 90};
    String[] requests2 = {
      "deposit 1613327630 3 400",
      "withdraw 1613327635 1 20",
      "withdraw 1613327651 1 50",
      "deposit 1613327655 1 50"
    };
    System.out.println(Arrays.toString(calculateBalances(balances2, requests2)));
  }

  public static final String WITHDRAW = "withdraw";
  public static final String DEPOSIT = "deposit";
  public static final long ONE_DAY = 24 * 60 * 60;
  public static final double TWO_PERCENTAGE = 0.02;

  public static int[] calculateBalances(int[] balances, String[] requests) {
    List<List<Long>> nextTimes = new ArrayList<>();
    List<List<Integer>> nextCashBacks = new ArrayList<>();
    int size = balances.length;
    for (int i = 0; i < size; i++) {
      nextTimes.add(new ArrayList<>());
      nextCashBacks.add(new ArrayList<>());
    }

    boolean invalidResult = false;

    for (int i = 0; i < requests.length; i++) {
      if (invalidResult) {
        return new int[] {-i};
      }

      String request = requests[i].trim();
      String requestType = request.startsWith(WITHDRAW) ? WITHDRAW : DEPOSIT;
      String[] requestStrArr = request.split(" ");

      long time = Long.parseLong(requestStrArr[1]);
      int accountId = Integer.parseInt(requestStrArr[2]);
      int amount = Integer.parseInt(requestStrArr[3]);
//      System.out.println(time + " " + accountId + " " + amount);

      if (WITHDRAW.equals(requestType)) {
        invalidResult =
            handleWithdraw(time, accountId - 1, amount, balances, nextTimes, nextCashBacks);
        //                System.out.println("accountId2Balances: " + accountId2Balances);
      } else if (DEPOSIT.equals(requestType)) {
        handleDeposit(accountId - 1, amount, balances);
        //                System.out.println("accountId2Balances: " + accountId2Balances);
      }
    }

    return balances;
  }

  private static void handleDeposit(int accountId, int amount, int[] balances) {
    balances[accountId] = balances[accountId] + amount;
  }

  private static boolean handleWithdraw(
      long time,
      int accountId,
      int amount,
      int[] balances,
      List<List<Long>> nextTimesList,
      List<List<Integer>> nextCashBacksList) {
    List<Long> nextTimes = nextTimesList.get(accountId);
    List<Integer> nextCashback = nextCashBacksList.get(accountId);

    List<Long> remainNextTimes = new ArrayList<>();
    List<Integer> remainNextCashback = new ArrayList<>();

    if (nextTimes != null && !nextTimes.isEmpty()) {
      for (int i = 0; i < nextTimes.size(); i++) {
        long nextTime = nextTimes.get(i);
        int cashBack = nextCashback.get(i);

        if (time - ONE_DAY < nextTime) {
          remainNextTimes.add(nextTime);
          remainNextCashback.add(cashBack);
          continue;
        }

        balances[accountId] = balances[accountId] + cashBack;
      }
    }

    if (balances[accountId] < amount) {
      return true;
    }

    balances[accountId] = balances[accountId] - amount;
    //        System.out.println("wwww accountId2Balances: " + accountId2Balances);

    remainNextTimes.add(time + ONE_DAY);
    nextTimesList.set(accountId, remainNextTimes);

    int newCashBacks = (int) (amount * TWO_PERCENTAGE);
    remainNextCashback.add(newCashBacks);
    nextCashBacksList.set(accountId, remainNextCashback);
    //        System.out.println("accountId2NextTime: " + accountId2NextTime);
    //        System.out.println("accountId2NextCashback: " + accountId2NextCashback);

    return false;
  }

}
