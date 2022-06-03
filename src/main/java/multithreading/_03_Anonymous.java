package multithreading;

public class _03_Anonymous {
  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            new Runnable() {
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
            });
    t1.start();

    Runnable task =
        () -> {
          runTask();
        };
    Thread t2 = new Thread(task);
    t2.start();

    Thread t3 = new Thread(() -> {runTask();});
    t3.start();
  }

  private static void runTask() {
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
