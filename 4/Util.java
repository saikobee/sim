import java.util.*;

public class Util {
    public static String join(Collection os, String sep) {
        Object[] xs = os.toArray();

        if (xs.length < 1) {
            return "";
        }
        else {
            int i;
            StringBuffer result = new StringBuffer();
            for (i = 0; i < xs.length - 1; i++) {
                result.append(xs[i]);
                result.append(sep);
            }
            result.append(xs[i]);

            return "" + result;
        }
    }
}
