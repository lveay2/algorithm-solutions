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
    Map<Integer, Integer> accountId2Balances = buildAccountId2BalancesMap(balances);
    //        System.out.println("accountId2Balances: " + accountId2Balances);
    Map<Integer, List<Long>> accountId2NextTime = new HashMap<>();
    Map<Integer, List<Integer>> accountId2NextCashback = new HashMap<>();
    boolean invalidResult = false;

    for (int i = 0; i < requests.length; i++) {
      if (invalidResult) {
        return new int[] {-i};
      }

      String request = requests[i].trim();
      String requestType = getRequestType(request);
      int accountId = getAccountId(request);
      long time = getTime(request);
      int amount = getAmount(request);
      //            System.out.println(time + " " + accountId + " " + amount);

      if (WITHDRAW.equals(requestType)) {
        invalidResult =
            handleWithdraw(
                time,
                accountId,
                amount,
                accountId2Balances,
                accountId2NextTime,
                accountId2NextCashback);
        //                System.out.println("accountId2Balances: " + accountId2Balances);
      } else if (DEPOSIT.equals(requestType)) {
        handleDeposit(accountId, amount, accountId2Balances);
        //                System.out.println("accountId2Balances: " + accountId2Balances);
      }
    }

    int l = balances.length;
    int[] result = new int[l];

    int index = 1;
    while (index <= l) {
      result[index - 1] = accountId2Balances.get(index++);
    }

    return result;
  }

  private static void handleDeposit(
      int accountId, int amount, Map<Integer, Integer> accountId2Balances) {
    accountId2Balances.put(accountId, accountId2Balances.get(accountId) + amount);
  }

  private static boolean handleWithdraw(
      long time,
      int accountId,
      int amount,
      Map<Integer, Integer> accountId2Balances,
      Map<Integer, List<Long>> accountId2NextTime,
      Map<Integer, List<Integer>> accountId2NextCashback) {
    List<Long> nextTimes = accountId2NextTime.get(accountId);
    List<Integer> nextCashback = accountId2NextCashback.get(accountId);

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

        accountId2Balances.put(accountId, accountId2Balances.get(accountId) + cashBack);
      }
    }

    if (accountId2Balances.get(accountId) < amount) {
      return true;
    }

    accountId2Balances.put(accountId, accountId2Balances.get(accountId) - amount);
    //        System.out.println("wwww accountId2Balances: " + accountId2Balances);

    remainNextTimes.add(time + ONE_DAY);
    accountId2NextTime.put(accountId, remainNextTimes);

    int newCashBacks = (int) (amount * TWO_PERCENTAGE);
    remainNextCashback.add(newCashBacks);
    accountId2NextCashback.put(accountId, remainNextCashback);
    //        System.out.println("accountId2NextTime: " + accountId2NextTime);
    //        System.out.println("accountId2NextCashback: " + accountId2NextCashback);

    return false;
  }

  private static int getAmount(String request) {
    String amount = request.split(" ")[3];
    return Integer.valueOf(amount);
  }

  private static long getTime(String request) {
    String time = request.split(" ")[1];
    return Long.valueOf(time);
  }

  private static int getAccountId(String request) {
    String accountId = request.split(" ")[2];
    return Integer.valueOf(accountId);
  }

  private static String getRequestType(String request) {
    if (request.startsWith(WITHDRAW)) {
      return WITHDRAW;
    } else if (request.startsWith(DEPOSIT)) {
      return DEPOSIT;
    }

    return null;
  }

  private static Map<Integer, Integer> buildAccountId2BalancesMap(int[] balances) {
    Map<Integer, Integer> accountId2Balances = new HashMap<>();
    for (int i = 1; i <= balances.length; i++) {
      accountId2Balances.put(i, balances[i - 1]);
    }
    return accountId2Balances;
  }
}
