package oa.other.account_manager;

public class Request {
    String type;
    long time;
    int accountId;
    int amount;

    public Request(String s1, String s2, String s3, String s4) {
        this.type = s1;
        this.time = Long.parseLong(s2);
        this.accountId = Integer.parseInt(s3);
        this.amount = Integer.parseInt(s4);
    }
    public Request(String type, long time, int accountId, int amount) {
        this.type = type;
        this.time = time;
        this.accountId = accountId;
        this.amount = amount;
    }


//    public static void processDeposit(int [] balances){
//        int index = this.accountId - 1;
//    }
}
