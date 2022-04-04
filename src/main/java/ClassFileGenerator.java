import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassFileGenerator {

  private static final String PACKAGE_LEETCODE = "leetcode";
  private static final String PACKAGE_EDUCATIVE = "educative";
  private static final String PACKAGE_LINTCODE = "lintcode";
  private static final String PACKAGE_OA = "oa";

  public static void main(String[] args) throws IOException {
    String problemName = "525. Contiguous Array\n";
    createLeetCodeProblemFile(problemName);

    problemName = "1495 · Leaf-Similar Trees";
    createLintCodeProblemFile(problemName);

    problemName = "Maximum Sum Subarray of Size K (easy)";
    createEducativeProblemFile(problemName);
  }

  // example className: 435. Non-overlapping Intervals\n
  private static void createLeetCodeProblemFile(String problemName) {
    createClassFile(problemName, PACKAGE_LEETCODE, "", true);
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
    rawName = rawName.replaceAll("\n", "").replaceAll("-", " ");

    String[] names = rawName.split(" ");
    int number = ClassFileGeneratorHelper.getProblemNumber(names[0].trim());
    String problemName = ClassFileGeneratorHelper.getProblemName(names, number);

    String classFileName =
        number != -1
            ? Constants.UNDER_SCORE + number + Constants.UNDER_SCORE + problemName
            : problemName;
    System.out.println("classFileName: " + classFileName + Constants.JAVA);

    subFolder = ClassFileGeneratorHelper.getSubFolder(subFolder, number);
    Path folderPath =
        Paths.get("").resolve(Constants.SRC_MAIN_JAVA).resolve(packageName).resolve(subFolder);
    System.out.println("folderPath: " + folderPath + "\n");

    if (create) {
      ClassFileGeneratorHelper.createProblemFile(folderPath, packageName, subFolder, classFileName);
    }
  }
}
