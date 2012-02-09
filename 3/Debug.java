import java.io.*;

public class Debug {
    private static final boolean     DEBUG = true;
    private static final PrintStream out   = System.out;

    public static void echo(Object... args) {
        if (! DEBUG) {
            return;
        }

        if (args.length < 1) {
            out.println();
        }

        for (int i = 0; i < (args.length - 1); i++) {
            out.print(args[i]);
            out.print(" ");
        }

        out.println(args[args.length - 1]);
    }

    public static void printf(String format, Object... args) {
        if (DEBUG) {
            out.printf(format, args);
        }
    }
}
