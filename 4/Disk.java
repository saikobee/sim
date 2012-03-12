import java.util.*;

public class Disk {
    private static PriorityQueue<Event> q = new PriorityQueue<Event>();

    public static void add(Event e) {
        q.add(e);
    }

    public static boolean isIdle() {
        return q.isEmpty();
    }

    public static Event poll() {
        return q.poll();
    }
}
