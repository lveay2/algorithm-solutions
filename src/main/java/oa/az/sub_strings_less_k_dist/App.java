package oa.az.sub_strings_less_k_dist;

import java.util.*;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(subStringsLessKDist("democracy", 5));
        System.out.println(subStringsLessKDist("wawaglknagagwunagkwkwagl", 4));
        ArrayList<ProductReviewScore> list = new ArrayList<ProductReviewScore>();
        list.add(new ProductReviewScore(1, 4));
        list.add(new ProductReviewScore(2, 9));
        list.add(new ProductReviewScore(1, 5));
        list.add(new ProductReviewScore(2, 8));
        list.add(new ProductReviewScore(2, 8));
        list.add(new ProductReviewScore(1, 6));
        list.add(new ProductReviewScore(1, 7));
        list.add(new ProductReviewScore(1, 8));
        list.add(new ProductReviewScore(1, 9));
        list.add(new ProductReviewScore(1, 10));
        list.add(new ProductReviewScore(2, 9.5));
        list.add(new ProductReviewScore(2, 10));
        list.add(new ProductReviewScore(2, 5));

        System.out.println(calculateHighestFive(100, list));
    }

    public static Map<Integer, Double> calculateHighestFive(int scoreCount, ArrayList<ProductReviewScore> reviewScoresOfProduct) {
        TreeMap<Integer, PriorityQueue<Double>> pqMap = new TreeMap<>();
        Map<Integer, Double> retVal = new HashMap<Integer, Double>();
        for (ProductReviewScore prs : reviewScoresOfProduct) {
            PriorityQueue<Double> pq = pqMap.getOrDefault(prs.productId, new PriorityQueue<Double>(5, new Comparator<Double>() {
                @Override
                public int compare(Double a, Double b) {
                    if (a > b) return -1;
                    else if (a < b) return 1;
                    else
                        return 0;
                }
            }));
            pq.offer(prs.reviewScore);
            pqMap.put(prs.productId, pq);
        }
        for (Map.Entry<Integer, PriorityQueue<Double>> entry : pqMap.entrySet()) {
            int n = 5;
            PriorityQueue<Double> pq = entry.getValue();
            double sum = 0.0;
            while (n > 0 && !pq.isEmpty()) {
                sum += pq.poll();
                n--;
            }
            retVal.put(entry.getKey(), sum / (5 - n));
        }
        return retVal;
    }

    public static List<String> subStringsLessKDist(String inputString, int num) {
        List<String> retVal = new LinkedList<String>();
        if (inputString == null || num > inputString.length()) return retVal;
        StringBuilder sb = new StringBuilder();
        int[] dict = new int[26];
        int distinct = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            sb.append(c);
            int n = c - 'a';
            dict[n]++;
            if (dict[n] == 1) {
                distinct++;
            }
            if (sb.length() == num) {
                if (num - distinct == 1) {
                    retVal.add(sb.toString());
                }
                int head = inputString.charAt(i - num + 1) - 'a';
                dict[head]--;
                if (dict[head] == 0) distinct--;
                sb.deleteCharAt(0);
            }
        }
        return retVal;
    }

    public static class ProductReviewScore {

        public int productId;
        public double reviewScore;

        public ProductReviewScore(int id, double value) {
            this.productId = id;
            this.reviewScore = value;
        }

    }

}
