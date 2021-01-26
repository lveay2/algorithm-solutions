package oa.instacart;

import java.util.*;

/**
 * Given an input of cards that have suits { +, -, = }, values { A, B, C }, and different counts of values [1 - 3].
 * Find a valid hand. A valid hand consists of 3 cards. Where all the suits are different or the same, all the values
 * are different or the same, and all counts are different or the same.
 *
 * https://leetcode.com/discuss/interview-question/554340/Instacart-or-Onsite-or-Create-Card-Hands
 *
 */
public class CardGame {

    static class Card {
        char prefix;
        char letter;
        int number;

        Card(char prefix,char letter,int number) {
            this.prefix = prefix;
            this.letter = letter;
            this.number = number;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.prefix);

            for(int i = 0; i <= number; i++) {
                sb.append(this.letter);
            }

            return sb.toString();
        }
    }

    private static List<Character> prefixes = Arrays.asList('-', '+', '=');
    private static List<Character> letters = Arrays.asList('A', 'B', 'C');

    private static String generateCard(List<String> cards) {
        if (cards == null || cards.size() == 0) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String card : cards) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (String card : cards) {
            Card firstCard = new Card(card.charAt(0), card.charAt(1), card.length() - 2);
            map.put(firstCard.toString(), map.get(firstCard.toString()) - 1);
            for (int i = 0; i <= 2; i++) {
                char prefix2nd = prefixes.get(i);
                for (int j= 0; j <= 2; j++) {
                    char letter2nd = letters.get(j);
                    for (int k = 0; k <= 2; k++) {
                        Card secondCard = new Card(prefix2nd, letter2nd, k);
                        if (isNotValid(secondCard, map)) {
                            continue;
                        }
                        map.put(secondCard.toString(), map.get(secondCard.toString()) - 1);

                        Card thirdCard = buildCard(firstCard, secondCard);
                        if (isNotValid(thirdCard, map)) {
                            map.put(secondCard.toString(), map.get(secondCard.toString()) + 1);
                            continue;
                        }

                        StringBuilder sb = new StringBuilder();
                        return sb.append(firstCard.toString()).append(" ").append(secondCard.toString()).append(" ")
                                .append(thirdCard.toString()).toString();
                    }
                }
            }
            map.put(firstCard.toString(), map.get(firstCard.toString()) + 1);
        }

        return null;
    }

    private static boolean isNotValid(Card thirdCard, Map<String, Integer> map) {
        if (!map.containsKey(thirdCard.toString())) {
            return true;
        }

        return map.get(thirdCard.toString()) <= 0;
    }

    private static Card buildCard(Card firstCard, Card secondCard) {
        char prefix1 = firstCard.prefix;
        char letter1 = firstCard.letter;
        int number1 = firstCard.number;

        char prefix2 = secondCard.prefix;
        char letter2 = secondCard.letter;
        int number2 = secondCard.number;

        char prefix3 = '\0';
        char letter3 = '\0';
        int number3 = -1;

        if (prefix1 == prefix2) {
            prefix3 = prefix1;
        } else {
            for (char prefix : prefixes) {
                if (prefix != prefix1 && prefix != prefix2) {
                    prefix3 = prefix;
                }
            }
        }

        if (letter1 == letter2) {
            letter3 = letter1;
        } else {
            for (char letter : letters) {
                if (letter != letter1 && letter != letter2) {
                    letter3 = letter;
                }
            }
        }

        if (number1 == number2) {
            number3 = number1;
        } else {
            number3 = 3 - number1 - number2;
        }

        return new Card(prefix3, letter3, number3);
    }

    private static List<String> generateCards(List<String> cards) {
        if (cards == null || cards.size() == 0) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String card : cards) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (String card : cards) {
            Card firstCard = new Card(card.charAt(0), card.charAt(1), card.length() - 2);
            map.put(firstCard.toString(), map.get(firstCard.toString()) - 1);
            for (int i = 0; i <= 2; i++) {
                char prefix2nd = prefixes.get(i);
                for (int j= 0; j <= 2; j++) {
                    char letter2nd = letters.get(j);
                    for (int k = 0; k <= 2; k++) {
                        Card secondCard = new Card(prefix2nd, letter2nd, k);
                        if (isNotValid(secondCard, map)) {
                            continue;
                        }
                        map.put(secondCard.toString(), map.get(secondCard.toString()) - 1);

                        Card thirdCard = buildCard(firstCard, secondCard);
                        if (isNotValid(thirdCard, map)) {
                            map.put(secondCard.toString(), map.get(secondCard.toString()) + 1);
                            continue;
                        }

                        StringBuilder sb = new StringBuilder();
                        result.add(sb.append(firstCard.toString()).append(" ").append(secondCard.toString()).append(" ")
                                .append(thirdCard.toString()).toString());
                    }
                }
            }
            map.put(firstCard.toString(), map.get(firstCard.toString()) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateCard(Arrays.asList("-A", "-B", "-BB", "+C", "-C", "-CC", "=CCC")));
        System.out.println(generateCards(Arrays.asList("-A", "-B", "-BB", "+C", "-C", "-CC", "=CCC")));
    }
}
