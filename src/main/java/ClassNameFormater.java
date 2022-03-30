import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ClassNameFormater {

    private static final String SRC_MAIN_JAVA = "src" + File.separator + "main" + File.separator + "java";
    private static final String UNDER_SCORE = "_";
    private static final String PACKAGE_LEETCODE = "leetcode";
    private static final String PACKAGE_EDUCATIVE = "educative";
    private static final String PACKAGE_LINTCODE = "lintcode";
    private static final String PACKAGE_OA = "oa";

    private static String packageName = null;
    private static String subFolder = null;
    private static boolean create = true;

    public static void main(String[] args) throws IOException {
        String className = "543. Diameter of Binary Tree";

        packageName = PACKAGE_LEETCODE;
        subFolder = "";
        createClassFile(className, packageName, subFolder, true);

        packageName = PACKAGE_LINTCODE;
        subFolder = "dynamic_programming";
//        subFolder = "dfs";
//        subFolder = "bfs";
//        subFolder = "system_design";
//        subFolder = "_100_good_problems";
//        subFolder = "two_pointers";
//        subFolder = "fb";
//        createClassFile(className, packageName, subFolder, create);

//        packageName = PACKAGE_EDUCATIVE;
//        subFolder = "dynamic_programming";
//        subFolder = "topological_sort";
//        subFolder = "subsets";
//        createClassFile(className, packageName, subFolder, create);
    }

    private static void createClassFile(String className, String packageName, String subFolder, boolean create) throws IOException {
        className = className.replace("\n", "");
        className = className.replaceAll("\\.", "");
        className = className.replaceAll("-", "");

        String[] names = className.split(" ");
        String number = names[0];

        if ("".equals(subFolder) || Character.isDigit(className.charAt(0))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < names.length; i++) {
                sb.append(names[i].substring(0,1).toUpperCase(Locale.ROOT) + names[i].substring(1));
            }

            className = UNDER_SCORE + number + UNDER_SCORE + sb.toString();
        }

        if ("".equals(subFolder)) {
            subFolder = getSubFolder(number);
        }

        className = className.replaceAll(" ", "");

        String javaClassName = className + ".java";
        System.out.println(javaClassName);

        Path folderPath =
                Paths.get("").resolve(SRC_MAIN_JAVA).resolve(packageName).resolve(subFolder);
        System.out.println(folderPath);

        if (create) {
            Files.createDirectories(folderPath);
            Path filePath = folderPath.resolve(javaClassName);

            List<String> contents =
                    Arrays.asList(
                            "package " + packageName + "." + subFolder + ";\n",
                            "/*",
                            "public class " + className + " {\n",
                            "    public static void main(String[] args) {",
                            "      ",
                            "    }\n\n",
                            "}");

            Files.write(
                    Paths.get(filePath.toFile().getAbsolutePath()),
                    contents,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    private static String getSubFolder(String number) {
        int num = Integer.parseInt(number);
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
