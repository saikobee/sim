import java.io.*;

public class Debug {
    private static final boolean DEBUG = true;
    private static final PrintStream out = System.out;

    public static void printf(String format, Object... args) {
        if (DEBUG) out.printf(format, args);
    }

    public static void println(Object sommat) {
        if (DEBUG) out.println(sommat);
    }
}
