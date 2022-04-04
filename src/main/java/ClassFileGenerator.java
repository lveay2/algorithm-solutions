import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ClassFileGenerator {

  private static final String SRC_MAIN_JAVA =
      "src" + File.separator + "main" + File.separator + "java";
  private static final String UNDER_SCORE = "_";
  private static final String PACKAGE_LEETCODE = "leetcode";
  private static final String PACKAGE_EDUCATIVE = "educative";
  private static final String PACKAGE_LINTCODE = "lintcode";
  private static final String PACKAGE_OA = "oa";
  private static String JAVA = ".java";

  public static void main(String[] args) throws IOException {
    String problemName = "438. Find All Anagrams in a String";
    createLeetCodeProblemFile(problemName);

    problemName = "1495 · Leaf-Similar Trees";
    createLintCodeProblemFile(problemName);

    problemName = "Maximum Sum Subarray of Size K (easy)";
    createEducativeProblemFile(problemName);
  }

  // example className: 435. Non-overlapping Intervals\n
  private static void createLeetCodeProblemFile(String problemName) {
    createClassFile(problemName, PACKAGE_LEETCODE, "", false);
  }

  // example className: 1495 · Leaf-Similar Trees
  private static void createLintCodeProblemFile(String problemName) {
    String subFolder = "dfs";
    //        subFolder = "bfs";
    //        subFolder = "system_design";
    //        subFolder = "_100_good_problems";
    //        subFolder = "two_pointers";
    //        subFolder = "fb";
    createClassFile(problemName, PACKAGE_LINTCODE, subFolder, false);
  }

  // example className: Maximum Sum Subarray of Size K (easy)
  private static void createEducativeProblemFile(String problemName) {
    String subFolder = "dynamic_programming";
    //            subFolder = "topological_sort";
    //            subFolder = "subsets";
    createClassFile(problemName, PACKAGE_EDUCATIVE, subFolder, false);
  }

  private static void createClassFile(
      String rawName, String packageName, String subFolder, boolean create) {
    rawName = rawName.replace("\n", "").replace("-", " ");

    String[] names = rawName.split(" ");
    int number = getProblemNumber(names[0].trim());
    String problemName = getProblemName(names, number);

    String classFileName =
        number != -1 ? UNDER_SCORE + number + UNDER_SCORE + problemName : problemName;
    System.out.println(classFileName + JAVA);

    subFolder = getSubFolder(subFolder, number);
    Path folderPath = Paths.get("").resolve(SRC_MAIN_JAVA).resolve(packageName).resolve(subFolder);
    System.out.println(folderPath);

    if (create) {
      createProblemFile(folderPath, packageName, subFolder, problemName);
    }
  }

  private static String getProblemName(String[] names, int number) {
    StringBuilder problemNameBuilder = new StringBuilder();
    for (int i = 0; i < names.length; i++) {
      if (number != -1 && i == 0) {
        continue;
      }

      if (!Character.isLetter(names[i].charAt(0))) {
        continue;
      }

      problemNameBuilder
          .append(names[i].substring(0, 1).toUpperCase(Locale.ROOT))
          .append(names[i].substring(1));
    }

    return problemNameBuilder.toString();
  }

  private static int getProblemNumber(String str) {
    if (Character.isDigit(str.charAt(0))) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < str.length(); i++) {
        if (Character.isDigit(str.charAt(i))) {
          sb.append(str.charAt(i));
        }
      }
      return Integer.parseInt(sb.toString());
    }

    return -1;
  }

  private static void createProblemFile(
      Path folderPath, String packageName, String subFolder, String problemName) {
    try {
      Files.createDirectories(folderPath);
      Path filePath = folderPath.resolve(problemName + JAVA);

      List<String> contents =
          Arrays.asList(
              "package " + packageName + "." + subFolder + ";\n",
              "/*",
              "public class " + problemName + " {\n",
              "    public static void main(String[] args) {",
              "      ",
              "    }\n\n",
              "}");

      Files.write(
          Paths.get(filePath.toFile().getAbsolutePath()),
          contents,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getSubFolder(String subFolder, int num) {
    if (!"".equals(subFolder)) {
      return subFolder;
    }

    int folderStart;
    if (num % 100 == 0) {
      folderStart = num - 100;
    } else {
      folderStart = num / 100 * 100;
    }
    List<String> folders =
        Arrays.asList(
            String.valueOf(folderStart == 0 ? 1 : folderStart + 1),
            String.valueOf(folderStart == 0 ? 100 : folderStart + 100));
    subFolder = UNDER_SCORE + String.join(UNDER_SCORE, folders);
    System.out.println("subFolder: " + subFolder);
    return subFolder;
  }
}
