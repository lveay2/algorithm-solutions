package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class _05_Synchronized {

  private AtomicInteger atomicInteger = new AtomicInteger(0);

  private int count;
  // intrinsic lock or monitor lock
  private synchronized void increment() {
    count++;
  }

  public static void main(String[] args) {
    _05_Synchronized app = new _05_Synchronized();
    app.doWork();
  }

  public void doWork() {
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 10_000; i++) {
                atomicInteger.getAndIncrement();
                increment();
              }
            });
    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < 10_000; i++) {
                atomicInteger.getAndIncrement();
                increment();
              }
            });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Count: " + count);
    System.out.println("atomicInteger: " + atomicInteger);
  }
}
