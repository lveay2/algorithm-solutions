package others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintABTest {

  public static void main(String[] args) {
    //        ExecutorService pool = new ThreadPoolExecutor(2, 200, 0L,
    //                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024),
    //                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    ExecutorService pool = Executors.newFixedThreadPool(2);

    PrintAB p = new PrintAB();

    pool.submit(
        () -> {
          for (int i = 0; i < 5; i++) {
            p.printA();
          }
        });
    pool.submit(
        () -> {
          for (int i = 0; i < 5; i++) {
            p.printB();
          }
        });

    pool.shutdown();
  }

  static class PrintAB {
    private volatile boolean flag = false;

    private synchronized void printA() {
      try {
        while (flag) {
          wait();
        }
        System.out.print("A");
        flag = true;
        notifyAll();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    private synchronized void printB() {
      try {
        while (!flag) {
          wait();
        }
        System.out.print("B");
        flag = false;
        notifyAll();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
