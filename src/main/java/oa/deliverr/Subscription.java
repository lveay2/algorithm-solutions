package oa.deliverr;

public class Subscription {
  public int id;
  public int customerId;
  public int monthlyPriceInDollars;
  public Subscription() {}
  public Subscription(int id, int customerId, int monthlyPriceInDollars) {
    this.id = id;
    this.customerId = customerId;
    this.monthlyPriceInDollars = monthlyPriceInDollars;
  }
}
