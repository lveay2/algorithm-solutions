package design_pattern.singleton;

public class DoubleCheckedSingleton {

    private static final Object lock = new Object();
    private volatile static DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    return new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }


}
