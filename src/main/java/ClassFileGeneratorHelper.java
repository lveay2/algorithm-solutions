import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ClassFileGeneratorHelper {

  public static String getProblemName(String[] names, int number) {
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

  public static int getProblemNumber(String str) {
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

  public static void createProblemFile(
      Path folderPath, String packageName, String subFolder, String problemName) {
    try {
      Files.createDirectories(folderPath);
      Path filePath = folderPath.resolve(problemName + Constants.JAVA);

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

  public static String getSubFolder(String subFolder, int num) {
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
    subFolder = Constants.UNDER_SCORE + String.join(Constants.UNDER_SCORE, folders);
    System.out.println("subFolder: " + subFolder);
    return subFolder;
  }
}
