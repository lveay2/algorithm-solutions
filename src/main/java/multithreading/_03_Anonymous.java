package multithreading;

public class _03_Anonymous {
  public static void main(String[] args) {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 0; i < 10; i++) {
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
  }
}
