import java.util.*;

public class Disk {
    private static PriorityQueue<Event> q = new PriorityQueue<Event>();
    private static boolean idle = true;

    public static String stringy() { return "[\n\t" + Util.join(q, ",\n\t") + "\n]"; };

    public static void add(Event e) {
        q.add(e);
    }

    public static boolean emptyQ() {
        return q.isEmpty();
    }

    public static int sizeQ() {
        return q.size();
    }

    public static boolean isIdle() { return idle; }

    public static void setIdle(boolean idle) {
        Disk.idle = idle;
        Debug.println("%%% THE DISK BECOME" + (!idle? " __NOT__ ": " ") + "IDLE");
    }

    public static Event poll() {
        return q.poll();
    }
}
