import java.util.*;

public class QuickTest {

  static int num = 0;

  public static void main(String[] args) {
    Queue<A> queue =
        new PriorityQueue<>(Comparator.comparingInt(A::getA).thenComparingInt(A::getB));
    queue.offer(new A(1, 2));
    queue.offer(new A(1, 1));
    queue.offer(new A(0, 1));
    queue.offer(new A(1, 100));
    while (!queue.isEmpty()) {
      System.out.println(queue.poll());
    }

    List<Boolean> l = new ArrayList<>();
    l.add(Boolean.parseBoolean("TRue"));

    System.out.println(l);

    String s = "word";
    //        System.out.println(s.substring(3, 2));

    if (num < 3) {
      num++;
      main(null);
    } else {
      return;
    }
    System.out.println("YES");

    try {
      System.out.println("A");
      badMethod();
      System.out.println("B");
    } catch (Exception e) {
      System.out.println("C");
    } finally {
      System.out.println("D");
    }
  }

  private static void badMethod() {
    throw new Error();
  }

  static class A {
    int a;
    int b;

    A(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public int getA() {
      return a;
    }

    public int getB() {
      return b;
    }

    @Override
    public String toString() {
      return "A{" + "a=" + a + ", b=" + b + '}';
    }
  }
}
