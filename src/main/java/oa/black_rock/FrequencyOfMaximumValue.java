package oa.black_rock;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrequencyOfMaximumValue {

    private static final String PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    /*
     * Complete the 'frequencyOfMaxValue' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER_ARRAY q
     */
    public static List<Integer> frequencyOfMaxValue(List<Integer> numbers, List<Integer> q) {
        int n = numbers.size();
        List<Integer> pos = new ArrayList<>(n);
        Map<Integer, Integer> n2Freq = new HashMap<>();

        int max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int num = numbers.get(i);
//            n2Freq[num]++;
            n2Freq.put(num, n2Freq.getOrDefault(num, 0) + 1);
            max = Math.max(max, num);
            pos.add(0, n2Freq.get(max));
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            result.add(pos.get(q.get(i) - 1));
        }

        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(frequencyOfMaxValue(Arrays.asList(2, 1, 2), Arrays.asList(1, 2, 3)));
//        System.out.println(frequencyOfMaxValue(Arrays.asList(5, 4, 5, 3, 2), Arrays.asList(1, 2, 3, 4, 5)));
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH + "input002.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH + "output.txt"));

        int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int qCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> q = IntStream.range(0, qCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = FrequencyOfMaximumValue.frequencyOfMaxValue(numbers, q);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

}
