package oa.instacart;

import leetcode._1_100._24_swap_nodes_in_pairs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=667022
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=615124&highlight=instacart
 */
public class CharacterMatrix {

    private static final String PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    private static List<String> readFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(PATH).resolve(fileName))) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!result.isEmpty() && !"".equals(result.get(result.size() - 1))) {
            result.add("");
        }

        return result;
    }

    private static String batchProcess(String file) {
        List<String> list = readFile(file);

        Map<Integer, Character> map = new TreeMap<>();
        List<String> tempList = new ArrayList<>();
        for (String s : list) {
            if ("".equals(s)) {
                Temp temp = decodeString(tempList);
                map.put(temp.key, temp.value);
                tempList = new ArrayList<>();
            } else {
                tempList.add(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Character> e : map.entrySet()) {
            sb.append(e.getValue());
        }

        return sb.toString();
    }

    private static Temp decodeString(List<String> tempList) {
        int key = Integer.valueOf(tempList.get(0));
        String[] coordinates = tempList.get(1).replace(" ", "").split(",");
        int x = Integer.valueOf(coordinates[0].substring(1));
        int y = Integer.valueOf(coordinates[1].substring(0, coordinates[1].length() - 1));

        return new Temp(key, tempList.get(tempList.size() - y - 1).charAt(x));
    }

    private static Character singleProcess(String file) {
        List<String> tempList = readFile(file);

        String[] coordinates = tempList.get(0).replace(" ", "").split(",");
        int x = Integer.valueOf(coordinates[0].substring(1));
        int y = Integer.valueOf(coordinates[1].substring(0, coordinates[1].length() - 1));

        return tempList.get(tempList.size() - y - 2).charAt(x);
    }


    public static void main(String[] args) {
        System.out.println(singleProcess("CharacterMatrix_input.txt"));
        System.out.println(singleProcess("CharacterMatrix_input1.txt"));
        System.out.println(batchProcess("CharacterMatrix_input2.txt"));
    }

    static class Temp {

        int key;
        char value;

        Temp(int key, char value) {
            this.key = key;
            this.value = value;
        }

    }

}
