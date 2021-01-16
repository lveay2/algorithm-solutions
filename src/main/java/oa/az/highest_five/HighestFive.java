package oa.az.highest_five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HighestFive {

    public static Map<Integer, Double> calculateHighestFive(int scoreCount, ArrayList<ProductReviewScore> reviewScoresOfProduct) {
        Map<Integer, Double> result = new HashMap<>();

        if (scoreCount == 0 || reviewScoresOfProduct == null || reviewScoresOfProduct.isEmpty()) return result;

        Map<Integer, PriorityQueue<Double>> map = new HashMap<>();

        for (ProductReviewScore score : reviewScoresOfProduct) {
            if (map.containsKey(score.productId)) {
                PriorityQueue<Double> queue = map.get(score.productId);

                queue.offer(score.reviewScore);
            } else {
                PriorityQueue<Double> queue = new PriorityQueue<Double>((d1, d2) -> -1 * d1.compareTo(d2));

                queue.offer(score.reviewScore);

                map.put(score.productId, queue);
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Double>> entry : map.entrySet()) {
            int productId = entry.getKey();

            PriorityQueue<Double> queue = entry.getValue();

            int count = 0;

            Double sum = 0.0;

            while (!queue.isEmpty() && count < 5) {
                Double value = queue.poll();

                System.out.println(value);

                sum += value;

                count++;
            }

            result.put(productId, sum / 5.0);

            System.out.println();
        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
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

        System.out.println(calculateHighestFive(13, list));
    }

}
