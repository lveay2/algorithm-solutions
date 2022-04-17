package multithreading;

import java.util.Scanner;

public class _04_volatile {
  public static void main(String[] args) {
    Processor proc1 = new Processor();
    proc1.start();

    System.out.println("Press return to stop");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();

    proc1.shutdown();
  }

  static class Processor extends Thread {

    // volatile is used to prevent threads caching the variable in its own thread
    private volatile boolean running = true;

    @Override
    public void run() {
      while (running) {
        System.out.println("Hello");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }

    public void shutdown() {
      running = false;
    }
  }
}
