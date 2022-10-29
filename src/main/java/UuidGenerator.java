import java.util.Locale;
import java.util.UUID;

public class UuidGenerator {
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      generate();
    }
  }

  private static void generate() {
    UUID uuid = UUID.randomUUID();
    String uuidStr = uuid.toString().toUpperCase(Locale.ROOT);
    uuidStr = uuidStr.replaceAll("-", "");
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= 20; i++) {
      sb.append(uuidStr.charAt(i));
      if (i % 5 == 0 && i < 20) {
        sb.append("-");
      }
    }
    System.out.println(sb);
  }
}
