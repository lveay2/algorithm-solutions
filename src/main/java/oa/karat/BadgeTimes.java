package oa.karat;

import java.util.*;

/*
We are working on a security system for a badged-access room in our company's building.

We want to find employees who badged into our secured room unusually often. We have an unordered
list of names and entry times over a single day. Access times are given as numbers up to four
digits in length using 24-hour time, such as "800" or "2250".

Write a function that finds anyone who badged into the room three or more times in
a one-hour period. Your function should return each of the employees who fit that
criteria, plus the times that they badged in during the one-hour period. If there
are multiple one-hour periods where this was true for an employee, just return
the earliest one for that employee.
 */
public class BadgeTimes {
    public static void main(String[] argv) {
        String[][] badgeTimes = new String[][] {
                {"Paul", "1355"},
                {"Jennifer", "1910"},
                {"Jose", "835"},
                {"Jose", "830"},
                {"Paul", "1315"},
                {"Chloe", "0"},
                {"Chloe", "1910"},
                {"Jose", "1615"},
                {"Jose", "1640"},
                {"Paul", "1405"},
                {"Jose", "855"},
                {"Jose", "930"},
                {"Jose", "915"},
                {"Jose", "730"},
                {"Jose", "940"},
                {"Jennifer", "1335"},
                {"Jennifer", "730"},
                {"Jose", "1630"},
                {"Jennifer", "5"},
                {"Chloe", "1909"},
                {"Zhang", "1"},
                {"Zhang", "10"},
                {"Zhang", "109"},
                {"Zhang", "110"},
                {"Amos", "1"},
                {"Amos", "2"},
                {"Amos", "400"},
                {"Amos", "500"},
                {"Amos", "503"},
                {"Amos", "504"},
                {"Amos", "601"},
                {"Amos", "602"},
                {"Paul", "1416"},
        };
        System.out.println(countIn1Hour(badgeTimes));

        //  Expected output (in any order)
        //  Paul: 1315 1355 1405
        //  Jose: 830 835 855 915 930
        //  Zhang: 10 109 110
        //  Amos: 500 503 504
    }
    // m -> 00:0n  01:0m
    // mm -> 00:nn 01:mm
    // hmm -> 0h:mm 0(h+1):mm
    // hhmm -> hh:mm (hh+1):mm
    // h <= time <= h+100
    private static Map<String, List<Integer>> countIn1Hour(String[][] badgeTimes) {
        Map<String, List<Integer>> result = new HashMap<>();

        Map<String, List<Integer>> badge2Times = new HashMap<>();
        for (int i = 0; i < badgeTimes.length; i++) {
            String badge = badgeTimes[i][0];
            int time = Integer.parseInt(badgeTimes[i][1]);
            if (!badge2Times.containsKey(badge)) {
                badge2Times.put(badge, new ArrayList<>());
            }
            List<Integer> times = badge2Times.get(badge);
            times.add(time);
        }

        System.out.println(badge2Times);

        for (Map.Entry<String, List<Integer>> entry : badge2Times.entrySet()) {
            String badge = entry.getKey();
            List<Integer> times = entry.getValue();

            if (times.size() < 3) {
                continue;
            }

            Collections.sort(times);
            System.out.println(badge + ": " + times);
            for (int i = 0; i < times.size() - 2; i++) {
                int start = times.get(i);
                int j = i + 2;

                if (times.get(j) - start > 100) {
                    continue;
                }

                while (j < times.size() && times.get(j) - start <=100) {
                    j++;
                }
                List<Integer> validTimes = new ArrayList<>();
                for (int k = i; k < j; k++) {
                    validTimes.add(times.get(k));
                }
                result.put(badge, validTimes);
                break;
            }
        }

        return result;
    }
}
