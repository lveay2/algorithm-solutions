package multithreading;

public class _01_Extends {
  public static void main(String[] args) {
    Run run1 = new Run();
    // start() will execute the method in its own thread
    run1.start();

    // run() will execute the method in main thread
    //    run1.run();

    Run run2 = new Run();
    // start() will execute the method in its own thread
    run2.start();
    run2.start();
  }

  static class Run extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        System.out.println("run " + i);
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
