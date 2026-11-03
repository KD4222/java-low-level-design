public class SingletonClass {
    private SingletonClass() {

    }

    private static class InstanceHolder {
        private static final SingletonClass INSTANCE = new SingletonClass();
    }

    public static SingletonClass getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
