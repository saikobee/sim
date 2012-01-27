public class Debug {
    private static final boolean DEBUG = false;

    public static void printf(String format, Object... args) {
        if (DEBUG) {
            System.out.printf(format, args);
        }
    }
}
