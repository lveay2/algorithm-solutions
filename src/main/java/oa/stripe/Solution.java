package oa.stripe;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


// Your previous Plain Text content is preserved below:

// At Stripe, we send a lot of invoices. For each invoice, we send multiple reminder emails. For this question, you will be outputting the subject line of each email we send for customers' invoices in sorted order. (Use any library/tool for sorting).

// Your invoicing system will need to be able to configure a reminder schedule. For example, you might want to send one email 10 days before the invoice comes out, one email when the invoice comes out, one email 10 days before the invoice is due, and one email when the invoice is due, which is 30 days from when the invoice first came out. This send schedule could look something like this, corresponding to the diagram below:

// ```
//      │
//      │
// t=-10│[Upcoming] Invoice for Alice
//      │
//      │
//      │
// t=0  │[New] Invoice for Alice
//      │
//      │
//      │
//      │
//      │
//      │
// t=20 │[Reminder] Invoice for Alice
//      │
//      │
//      │
// t=30 │[Due] Invoice for Alice
//      │
//      │
//      │
//      │
//      ▼
// ```

// Given a configuration for sending emails, your input will be an unsorted list of unique customer invoices with times for when their `[New]` invoice should come out and how much we are charging them in dollars. Your objective is to print out the subject lines of all emails we will send out in sorted order by time. Your output should include the time, the type of email, the customer, and amount due.

// Here is a sample of how your system should behave:

// ```
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
// ```

// Output:
// ```
// -10: [Upcoming] Invoice for Alice for 200 dollars
// -9: [Upcoming] Invoice for Bob for 100 dollars
// 0: [New] Invoice for Alice for 200 dollars
// 1: [New] Invoice for Bob for 100 dollars
// 20: [Reminder] Invoice for Alice for 200 dollars
// 21: [Reminder] Invoice for Bob for 100 dollars
// 30: [Due] Invoice for Alice for 200 dollars
// 31: [Due] Invoice for Bob for 100 dollars
// ```
// /*
// list<Integer> invoiceTimes list<String> emailType
// -10
// Upcoming
// for i in invoiceTimes
// -10
// Upcoming
//   for i in customer_invoices
//     print j + i: [<emailType>] Invoice for <name> for <amount> dollars
// */

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Map<Integer, String> sendSchedule = new HashMap<>();
        sendSchedule.put(-10, "Upcoming");
        sendSchedule.put(0, "New");
        sendSchedule.put(20, "Reminder");
        sendSchedule.put(30, "Due");

        Invoicer invoicer = Invoicer.newInstance(sendSchedule);

        // customer_invoices = [
//     {"invoice_time": 0, "name": "Alice", "amount": 200},
//     {"invoice_time": 1, "name": "Bob", "amount": 100},
// ]
        Map<String, String> customer_invoice = new HashMap<>();
        customer_invoice.put("invoice_time", "0");
        customer_invoice.put("name", "Alice");
        customer_invoice.put("amount", "200");

        Map<String, String> customer_invoice2 = new HashMap<>();
        customer_invoice2.put("invoice_time", "1");
        customer_invoice2.put("name", "Bob");
        customer_invoice2.put("amount", "100");

        List<Map<String, String>> customer_invoices = new ArrayList<>();
        customer_invoices.add(customer_invoice);
        customer_invoices.add(customer_invoice2);


// customer_payments = [
//     {:payment_time=>-9, :name=>"Alice", :amount=>100},
//     {:payment_time=>1, :name=>"Alice", :amount=>50},
//     {:payment_time=>0, :name=>"Bob", :amount=>100},
// ]


        Map<String, String> customer_payment = new HashMap<>();
        customer_payment.put("payment_time", "-9");
        customer_payment.put("name", "Alice");
        customer_payment.put("amount", "100");
        Map<String, String> customer_payment1 = new HashMap<>();
        customer_payment1.put("payment_time", "1");
        customer_payment1.put("name", "Alice");
        customer_payment1.put("amount", "50");
        Map<String, String> customer_payment2 = new HashMap<>();
        customer_payment2.put("payment_time", "0");
        customer_payment2.put("name", "Bob");
        customer_payment2.put("amount", "100");

        List<Map<String, String>> customer_payments = new ArrayList<>();
        customer_payments.add(customer_payment);
        customer_payments.add(customer_payment1);
        customer_payments.add(customer_payment2);

        invoicer.sendEmails(customer_invoices, customer_payments);
    }
}
