import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ClassNameFormater {

    private static final String SRC_MAIN_JAVA = "src" + File.separator + "main" + File.separator + "java";
    private static final String UNDER_SCORE = "_";
    private static final String PACKAGE_LEETCODE = "leetcode";
    private static final String PACKAGE_EDUCATIVE = "educative";
    private static final String PACKAGE_LINTCODE = "lintcode";
    private static final String PACKAGE_OA = "oa";

    private static String packageName = null;
    private static String subFolder = null;

    public static void main(String[] args) throws IOException {
        String className = "111. Climbing Stairs\n";

//        packageName = PACKAGE_LEETCODE;
//        subFolder = "";

//        packageName = PACKAGE_LINTCODE;
//        subFolder = "dynamic_programming";
//        subFolder = "dfs";
//        subFolder = "bfs";
//        subFolder = "system_design";
//        subFolder = "_100_good_problems";
//        subFolder = "twopointers";
//        subFolder = "fb";

        packageName = PACKAGE_EDUCATIVE;
        subFolder = "dynamic_programming";

        createClassFile(className, packageName, subFolder);
    }

    private static void createClassFile(String className, String packageName, String subFolder) throws IOException {
        className = className.replace("\n", "");
        className = className.replaceAll("\\.", "");
        className = className.replaceAll(" ", "_");

        if ("".equals(subFolder)) {
            subFolder = getSubFolder(className);
        }

        className = UNDER_SCORE + className;
        String javaClassName = className + ".java";

        System.out.println(className);

        Path folderPath =
                Paths.get("").resolve(SRC_MAIN_JAVA).resolve(packageName).resolve(subFolder);
        System.out.println(folderPath);
        Files.createDirectories(folderPath);
        Path filePath = folderPath.resolve(javaClassName);

        List<String> contents =
                Arrays.asList(
                        "package " + packageName + "." + subFolder + ";\n",
                        "public class " + className + " {\n\n\n",
                        "    public static void main(String[] args) {",
                        "      ",
                        "    }",
                        "}");

        Files.write(
                Paths.get(filePath.toFile().getAbsolutePath()),
                contents,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String getSubFolder(String className) {
        int num = Integer.parseInt(className.split("_")[0]);
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
        String subFolder = UNDER_SCORE + String.join(UNDER_SCORE, folders);
        System.out.println("subFolder: " + subFolder);
        return subFolder;
    }

}
