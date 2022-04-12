import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassFileGenerator {

  private static final String PACKAGE_LEETCODE = "leetcode";
  private static final String PACKAGE_EDUCATIVE = "educative";
  private static final String PACKAGE_LINTCODE = "lintcode";
  private static final String PACKAGE_OA = "oa";

  public static void main(String[] args) throws IOException {
    String problemName = "560. Subarray Sum Equals K";
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
    String parentFolder = "dfs";
    //    parentFolder = "bfs";
    //    parentFolder = "system_design";
    //    parentFolder = "_100_good_problems";
    //    parentFolder = "two_pointers";
    //    parentFolder = "fb";
    createClassFile(problemName, PACKAGE_LINTCODE, parentFolder, false);
  }

  // example className: Maximum Sum Subarray of Size K (easy)
  private static void createEducativeProblemFile(String problemName) {
    String parentFolder = "dynamic_programming";
    //    parentFolder = "topological_sort";
    //    parentFolder = "subsets";
    createClassFile(problemName, PACKAGE_EDUCATIVE, parentFolder, false);
  }

  private static void createClassFile(
      String rawName, String packageName, String parentFolder, boolean createNow) {
    rawName = rawName.replaceAll("\n", "").replaceAll("-", " ");

    String[] names = rawName.split(" ");
    int number = ClassFileGeneratorHelper.getProblemNumber(names[0].trim());
    String problemName = ClassFileGeneratorHelper.getProblemName(names, number);

    String classFileName =
        number != -1
            ? Constants.UNDER_SCORE + number + Constants.UNDER_SCORE + problemName
            : problemName;
    System.out.println("classFileName: " + classFileName + Constants.JAVA);

    parentFolder = ClassFileGeneratorHelper.getParentFolder(parentFolder, number);
    Path folderPath =
        Paths.get("").resolve(Constants.SRC_MAIN_JAVA).resolve(packageName).resolve(parentFolder);
    System.out.println("folderPath: " + folderPath + "\n");

    if (createNow) {
      ClassFileGeneratorHelper.createProblemFile(
          folderPath, packageName, parentFolder, classFileName);
    }
  }
}
