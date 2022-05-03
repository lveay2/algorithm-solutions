package oa.stripe;

import java.util.*;

public class Invoicer {

  // send_schedule = {
  //   -10: "Upcoming",
  //   0: "New",
  //   20: "Reminder",
  //   30: "Due"
  // }

  // invoicer = Invoicer.new(send_schedule)

  // customer_invoices = [
  //     {"invoice_time": 0, "name": "Alice", "amount": 200},
  //     {"invoice_time": 1, "name": "Bob", "amount": 100},
  // ]
  // invoicer.send_emails(customer_invoices)
  static Map<Integer, String> time2Type = new TreeMap<>();

  public Invoicer() {}

  public static Invoicer newInstance(Map<Integer, String> sendSchedule) {
    time2Type = sendSchedule;
    Invoicer invoicer = new Invoicer();
    return invoicer;
  }

  // invoicer.send_emails(customer_invoices)
  public void sendEmails(List<Map<String, String>> customerInvoices) {
    for (Map.Entry<Integer, String> e : time2Type.entrySet()) {
      String message = "%d: [%s] Invoice for %s for %d dollars";

      int dateDiff = e.getKey();
      String emailType = e.getValue();
      for (Map<String, String> customerInvoice : customerInvoices) {
        int invoice_time = Integer.valueOf(customerInvoice.get("invoice_time"));
        invoice_time += dateDiff;
        String name = customerInvoice.get("name");
        int amount = Integer.valueOf(customerInvoice.get("amount"));
        System.out.println(String.format(message, invoice_time, emailType, name, amount));
      }
    }
  }

  public void sendEmails(
      List<Map<String, String>> customerInvoices, List<Map<String, String>> customerPayments) {
    for (Map.Entry<Integer, String> e : time2Type.entrySet()) {
      String message = "%d: [%s] Invoice for %s for %d dollars";

      int dateDiff = e.getKey();
      String emailType = e.getValue();
      for (Map<String, String> customerInvoice : customerInvoices) {
        int invoice_time = Integer.valueOf(customerInvoice.get("invoice_time"));
        invoice_time += dateDiff;
        String name = customerInvoice.get("name");
        int amount = Integer.valueOf(customerInvoice.get("amount"));

        int payment_total = 0;
        for (Map<String, String> customerPayment : customerPayments) {
          int payment_time = Integer.valueOf(customerPayment.get("payment_time"));
          if (name == customerPayment.get("name") && payment_time <= invoice_time) {
            payment_total += Integer.valueOf(customerPayment.get("amount"));
          }
        }

        if (payment_total >= amount) {
          continue;
        }
        System.out.println(
            String.format(message, invoice_time, emailType, name, amount - payment_total));
      }
    }
  }
}

// # Part 2
// Customers sometimes make a series of payments to pay their invoice. In this part, you will have
// an unsorted list of payments made by the customers, specifying their name, time of payment, and
// payment amount. Each subject line should now accurately reflect how much money is still owed by
// the customer. For example, if Bob pays half of his invoice right when it comes out, his next
// reminder email will say that he owes 50 dollars, not 100. If a customer has fully paid their
// invoice, we do not want to send them any more emails. In addition to returning the sequence of
// emails sent, you will also need to return a list of delinquent customers, those who did not fully
// pay their invoice before the due date, and how much they owe.

// ```
// customer_payments = [
//     {"payment_time": -9, "name": "Alice", "amount": 100},
//     {"payment_time": 1, "name": "Alice", "amount": 50},
//     {"payment_time": 0, "name": "Bob", "amount": 100},
// ]
// invoicer.send_emails(customer_invoices, customer_payments)
// ```

// Output:
// ```
// -10: [Upcoming] Invoice for Alice for 200 dollars
// -9: [Upcoming] Invoice for Bob for 100 dollars
// 0: [New] Invoice for Alice for 100 dollars
// 20: [Reminder] Invoice for Alice for 50 dollars
// 30: [Due] Invoice for Alice for 50 dollars
// Delinquent customers:
// Alice owes 50 dollars
// ```
