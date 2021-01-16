package design_pattern.singleton;

public class PreInitializedSingleton {

    private static final PreInitializedSingleton instance = new PreInitializedSingleton();

    private PreInitializedSingleton() {
    }

    public static PreInitializedSingleton getInstance() {
        return instance;
    }

}
