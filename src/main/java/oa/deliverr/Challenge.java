package oa.deliverr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
Calculating invoices for customer billing
Background
In the past, we provided some raw billing data in JSON format to the finance team, which they used to manually generate monthly invoices for our customers. Recently, they’ve asked us to create some automation to make this process less error-prone.

Instructions
Your goal is to implement the billFor function to calculate the total monthly bill for a customer.

Customers are billed based on their subscription tier for the month. We charge them a prorated amount for each user who was active during that month.

You talked with the other engineers on the team and decided that the following algorithm would work:

Calculate a daily rate for the active subscription tier
For each day of the month, identify which users were active that day
Multiply the number of active users for the day by the daily rate to calculate the total for the day
Return the running total for the month at the end, rounded to 2 decimal places
Parameters
This billing function accepts the following parameters:

month
Always present. Has the following structure:

"2019-01"   // January 2019 in YYYY-MM format
activeSubscription
May be null. If present, has the following structure:

// see Subscription class
{
  id: 1,
  customerId: 1,
  monthlyPriceInDollars: 4, // price per active user per month
}
users
May be empty, but not null. Has the following structure:

// see User class
[
  {
    id: 1,
    name: "Employee #1",
    customerId: 1,

    // when this user started
    activatedOn: LocalDate.of(2018, 11, 4),

    // last day to bill for user
    // should bill up to and including this date
    // since user had some access on this date
    deactivatedOn: LocalDate.of(2019, 1, 10)
  },
  {
    id: 2,
    name: "Employee #2",
    customerId: 1,

    // when this user started
    activatedOn: LocalDate.of(2018, 12, 4),

    // hasn't been deactivated yet
    deactivatedOn: null
  }
]
Return value
This function should return the total monthly bill for the customer, rounded to 2 decimal places.

If there are no users or the subscription is not present, the function should return 0 since the customer does not owe anything for that month.

Calculation Examples
Here is an example of calculating the amount to bill each customer using the algorithm described above. This example is captured by the "works when a user is activated during the month" test.

Month	2019-01
Subscription tier	$4 / month
User activations	2018-11-04
2018-12-04
2019-01-10 (new this month)
User deactivations	None
Daily rate is $0.129032258   (this is the monthly rate of $4.00 / 31 days in January)

2019-01-01  2 active users * $0.129032258 = $0.258064516  (subtotal: $0.258064516)
2019-01-02  2 active users * $0.129032258 = $0.258064516  (subtotal: $0.516129032)
...
2019-01-09  2 active users * $0.129032258 = $0.258064516  (subtotal: $2.322580645)
2019-01-10  3 active users * $0.129032258 = $0.387096774  (subtotal: $2.709677419)
2019-01-11  3 active users * $0.129032258 = $0.387096774  (subtotal: $4.387096772)
...
2019-01-30  3 active users * $0.129032258 = $0.387096774  (subtotal: $10.451612903)
2019-01-31  3 active users * $0.129032258 = $0.387096774  (subtotal: $10.838709677)

Total = $10.84 (round subtotal to nearest cent)
Testing
The automated tests we provide only cover a few key cases, so you should plan to add some of your own tests or modify the existing ones to ensure that your solution handles any edge cases. You should be able to follow the existing patterns for naming and constructing tests to add your own.

Notes / Edge cases
It’s more important for the return value to be correct than it is for the algorithm to be highly optimized.
You can store intermediate results as any kind of decimal type (e.g. float, double). You do not need to round values until the last step.
You should bill for all days between and including both the activation and deactivation dates since the user still had some access on the last day.
You should not change function names or return types of the provided functions since our test cases depend on those not changing.
 */
public class Challenge {
    public static double billFor(String month, Subscription activeSubscription, User[] users) {
        float total = 0.0f;

        if (activeSubscription == null) {
            return total;
        }

        // parse date
        String dateStr = month + "-01";
        LocalDate billDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDay = firstDayOfMonth(billDate);
        LocalDate endDay = lastDayOfMonth(billDate);

        long daysDiff = ChronoUnit.DAYS.between(startDay, endDay) + 1;
        double dailyRate = activeSubscription.monthlyPriceInDollars * 1.0 / daysDiff;
//        System.out.println("dailyRate " + dailyRate);
        int totalDays = 0;
        for (User user : users) {
            if (user.customerId != activeSubscription.customerId) {
                continue;
            }
            // [deactivatedOn    activatedOn]
            if (user.activatedOn != null && user.deactivatedOn != null && user.activatedOn.isAfter(user.deactivatedOn)) {
                continue;
            }
            // [startDay                  endDay                             ]
            // [                                   activatedOn  deactivatedOn]
            if (user.activatedOn == null || (user.activatedOn != null && user.activatedOn.isAfter(endDay))) {
                continue;
            }
            // [                                startDay    endDay]
            // [activatedOn     deactivatedOn                     ]
            if (user.deactivatedOn != null && user.deactivatedOn.isBefore(startDay)) {
                continue;
            }
            // [                startDay            endDay]
            // [activatedOn                               ]
            if (user.activatedOn.isBefore(startDay)) {
                // [                startDay                    endDay]
                // [activatedOn                deactivatedOn          ]
                if (user.deactivatedOn != null && user.deactivatedOn.isBefore(endDay)) {
                    daysDiff = ChronoUnit.DAYS.between(startDay, user.deactivatedOn) + 1;
                }
            }
            // [startDay                  endDay]
            // [            activatedOn         ]
            if (user.activatedOn.isAfter(startDay) && user.activatedOn.isBefore(endDay)) {
                // [ startDay                                      endDay]
                // [             activatedOn     deactivatedOn           ]
                if (user.deactivatedOn != null && user.deactivatedOn.isBefore(endDay)) {
                    daysDiff = ChronoUnit.DAYS.between(user.activatedOn, user.deactivatedOn) + 1;
                }
                // [ startDay                   endDay                ]
                // [             activatedOn             deactivatedOn]
                else {
                    daysDiff = ChronoUnit.DAYS.between(user.activatedOn, endDay) + 1;
                }
            }

            totalDays += daysDiff;
        }

        return (double) Math.round(dailyRate * totalDays * 100) / 100;
    }

    /*******************
     * Helper functions *
     *******************/

    /**
     * Takes a LocalDate object and returns a LocalDate which is the first day
     * of that month. For example:
     * <p>
     * firstDayOfMonth(LocalDate.of(2019, 2, 7)) // => LocalDate.of(2019, 2, 1)
     **/
    private static LocalDate firstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * Takes a LocalDate object and returns a LocalDate which is the last day
     * of that month. For example:
     * <p>
     * lastDayOfMonth(LocalDate.of(2019, 2, 7)) // => LocalDate.of(2019, 2, 28)
     **/
    private static LocalDate lastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * Takes a LocalDate object and returns a LocalDate which is the next day.
     * For example:
     * <p>
     * nextDay(LocalDate.of(2019, 2, 7))  // => LocalDate.of(2019, 2, 8)
     * nextDay(LocalDate.of(2019, 2, 28)) // => LocalDate.of(2019, 3, 1)
     **/
    private static LocalDate nextDay(LocalDate date) {
        return date.plusDays(1);
    }
}
