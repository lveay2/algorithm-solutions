package oa.other.account_manager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AccountManager {
  public static void main(String[] args) {
    // EXAMPLE 1
    int[] balances = new int[] {1000, 1500};
    String[] requests = new String[] {
        "withdraw 1613327630 2 480",
        "withdraw 1613327644 2 800",
        "withdraw 1614105244 1 100",
        "deposit 1614108844 2 200",
        "withdraw 1614108845 2 150"
    };

    // EXAMPLE 2
    //        int [] balances = new int [] {20,1000,500,40,90};
    //        String [] requests = new String [] {
    //                "deposit 1613327603 3 400",
    //                "withdraw 1613327635 1 20",
    //                "withdraw 1613327651 1 50",
    //                "deposit 1613327655 1 50"
    //        };
    int[] remainBalances = calculateBalances(balances, requests);
    System.out.println(Arrays.toString(remainBalances));
    //        for(int i = 0; i< remainBalances.length; i++){
    //            System.out.println(remainBalances[i] + " ");
    //        }

    balances = new int[] {20, 1000, 500, 40, 90};
    requests = new String[] {
        "deposit 1613327630 3 400",
        "withdraw 1613327635 1 20",
        "withdraw 1613327651 1 50",
        "deposit 1613327655 1 50"
    };
    System.out.println(Arrays.toString(calculateBalances(balances, requests)));
    System.out.println("-----------------");

    balances = new int[] {300};
    requests = new String[] {
        "withdraw 1613327630 1 100",
        "withdraw 1613327644 1 200"
    };
    System.out.println(Arrays.toString(calculateBalances(balances, requests)));
    System.out.println("-----------------");

    balances = new int[] {300};
    requests = new String[] {
        "withdraw 1613327630 1 100",
        "withdraw 1613327644 1 202",
        "withdraw 1613414030 1 200"
    };
    System.out.println(Arrays.toString(calculateBalances(balances, requests)));
    System.out.println("-----------------");

    balances = new int[] {300};
    requests = new String[] {
        "withdraw 1613327630 1 100",
        "withdraw 1613414030 1 202"
    };
    System.out.println(Arrays.toString(calculateBalances(balances, requests)));
  }

  private static final String D = "deposit";
  private static final String W = "withdraw";
  private static final long ONE_DAY = 24 * 60 * 60;
  private static final double TWO_PERCENT = 0.02;
  private static Queue<Request> cashbackQueue = new LinkedList<>();

  public static int[] calculateBalances(int[] balances, String[] requests) {
    boolean invalidResult = false;
    for (int i = 0; i < requests.length; i++) {
      Request r =
          new Request(
              requests[i].split(" ")[0],
              requests[i].split(" ")[1],
              requests[i].split(" ")[2],
              requests[i].split(" ")[3]);
      // check cashback//
      if (!cashbackQueue.isEmpty() && cashbackQueue.peek().time <= r.time) {
        processCashback(balances, cashbackQueue.poll());
      }

      if (r.type.equals(D)) {
        processDeposit(balances, r);
      } else if (r.type.equals(W)) {
        invalidResult = processWithdraw(balances, r);
      }
      if (invalidResult) {
        int index = i + 1;
        return new int[] {-index};
      }
    }
    return balances;
  }

  private static void processDeposit(int[] balances, Request r) {
    balances[r.accountId - 1] += r.amount;
  }

  private static boolean processWithdraw(int[] balances, Request r) {
    if (r.amount > balances[r.accountId - 1]) {
      return true;
    }

    balances[r.accountId - 1] -= r.amount;
    generateCashback(r.time, r.accountId, r.amount);
    return false;
  }

  private static void generateCashback(long time, int accountId, int amount) {
    long cashbackTime = time + ONE_DAY;
    int cashbackAmount = (int) (amount * TWO_PERCENT);
    Request cashback = new Request("cashback", cashbackTime, accountId, cashbackAmount);
    cashbackQueue.add(cashback);
  }

  private static void processCashback(int[] balances, Request r) {
    balances[r.accountId - 1] += r.amount;
  }
}
