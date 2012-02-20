import java.util.*;

public class Sim {
    private static final PriorityQueue<Event> eventQ = new PriorityQueue<Event>();

    private static int evens = 0;
    private static int odds  = 0;

    private static int n = 0;

    public static void setN(int m) {
        n = m;
    }

    public static Event makeEvent(long time, int n) {
        return n % 2 == 0
            ? new EvenEvent(time, n)
            : new OddEvent (time, n);
    }

    public static long time;

    public static boolean empty() {
        return eventQ.isEmpty();
    }

    public static Event event() {
        return eventQ.poll();
    }

    public static void incEvens() { evens++; }
    public static void incOdds () { odds ++; }

    public static void reset() {
        eventQ.clear();
        evens = 0;
        odds  = 0;
    }

    public static void add(Event e) {
        eventQ.offer(e);
    }

    public static void stats() {
        System.out.printf(
            "total time = %d\n" +
            "evens = %d\n" +
            "odds  = %d\n",
            time,
            evens,
            odds
        );
    }

    public static void display() {
        System.out.printf("n = %d\n", n);
    }
}
