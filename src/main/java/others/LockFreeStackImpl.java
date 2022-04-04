package others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/** https://www.geeksforgeeks.org/lock-free-stack-using-java/ */
public class LockFreeStackImpl {

  public static void main(String[] args) throws InterruptedException {

    // Defining two stacks
    // Uncomment the following line to see the standard stack implementation.
    //        ClassicStack<Integer> operStack = new ClassicStack<>();

    // Lock-Free Stack definition.
    LockFreeStack<Integer> operStack = new LockFreeStack<>();
    Random randomIntegerGenerator = new Random();

    for (int j = 0; j < 10; j++) {
      operStack.push(randomIntegerGenerator.nextInt());
    }

    // Defining threads for Stack Operations
    List<Thread> threads = new ArrayList<>();
    int stackPushThreads = 2;
    int stackPopThreads = 2;

    for (int k = 0; k < stackPushThreads; k++) {
      Thread pushThread =
          new Thread(
              () -> {
                System.out.println("Pushing into stack...");

                while (true) {
                  operStack.push(randomIntegerGenerator.nextInt());
                }
              });

      // making the threads low priority before
      // starting them
      pushThread.setDaemon(true);
      threads.add(pushThread);
    }

    for (int k = 0; k < stackPopThreads; k++) {
      Thread popThread =
          new Thread(
              () -> {
                System.out.println("Popping from stack ...");
                while (true) {
                  operStack.pop();
                }
              });

      popThread.setDaemon(true);
      threads.add(popThread);
    }

    for (Thread thread : threads) {
      thread.start();
    }
    Thread.sleep(500);

    System.out.println(
        "The number of stack operations performed in 1/2 a second-->"
            + operStack.getNoOfOperations());
  }

  // Class defining the implementation of Lock Free Stack
  private static class LockFreeStack<T> {

    // Defining the stack nodes as Atomic Reference
    private final AtomicReference<StackNode<T>> headNode = new AtomicReference<>();
    private final AtomicInteger noOfOperations = new AtomicInteger(0);

    public int getNoOfOperations() {
      return noOfOperations.get();
    }

    // Push operation
    public void push(T value) {
      StackNode<T> newHead = new StackNode<T>(value);

      // CAS loop defined
      while (true) {
        StackNode<T> currentHeadNode = headNode.get();
        newHead.next = currentHeadNode;

        // perform CAS operation before setting new value
        if (headNode.compareAndSet(currentHeadNode, newHead)) {
          break;
        } else {
          // waiting for a nanosecond
          LockSupport.parkNanos(1);
        }
      }

      // getting the value atomically
      noOfOperations.incrementAndGet();
    }

    // Pop function
    public T pop() {
      StackNode<T> currentHeadNode = headNode.get();

      // CAS loop defined
      while (currentHeadNode != null) {
        StackNode<T> newHead = currentHeadNode.next;
        if (headNode.compareAndSet(currentHeadNode, newHead)) {
          break;
        } else {
          // waiting for a nanosecond
          LockSupport.parkNanos(1);
          currentHeadNode = headNode.get();
        }
      }
      noOfOperations.incrementAndGet();
      return currentHeadNode != null ? currentHeadNode.value : null;
    }
  }

  // Class defining the implementation of a Standard stack for concurrency
  private static class ClassicStack<T> {

    private StackNode<T> headNode;

    private int noOfOperations;

    // Synchronizing the operations for concurrency control
    public synchronized int getNoOfOperations() {
      return noOfOperations;
    }

    public synchronized void push(T number) {
      StackNode<T> newNode = new StackNode<T>(number);
      newNode.next = headNode;
      headNode = newNode;
      noOfOperations++;
    }

    public synchronized T pop() {
      if (headNode == null) return null;
      else {
        T val = headNode.getValue();
        StackNode<T> temp = headNode;
        headNode = headNode.next;
        temp.next = null;
        noOfOperations++;
        return val;
      }
    }
  }

  private static class StackNode<T> {
    T value;
    StackNode<T> next;

    StackNode(T value) {
      this.value = value;
    }

    public T getValue() {
      return this.value;
    }
  }
}
