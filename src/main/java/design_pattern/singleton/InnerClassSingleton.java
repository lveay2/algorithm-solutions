package design_pattern.singleton;

public class InnerClassSingleton {

  private InnerClassSingleton() {}

  public static InnerClassSingleton getInstance() {
    return Holder.instance;
  }

  private static class Holder {

    private static final InnerClassSingleton instance = new InnerClassSingleton();
  }
}
