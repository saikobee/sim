public class Debug {
    private static final boolean DEBUG = true;

    public static void printf(String format, Object... args) {
        if (DEBUG) {
            System.out.printf(format, args);
        }
    }
}
