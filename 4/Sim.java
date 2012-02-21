import java.util.*;

public class Sim {
    private static final PriorityQueue<Event> eventQ = new PriorityQueue<Event>();

    public static long evens = 0;
    public static long odds  = 0;

    private static long n = 0;

    public static void setN(long m) {
        n = m;
    }

    public static Event makeEvent(long time, long n) {
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
            "time  = %d\n" +
            "evens = %d\n" +
            "odds  = %d\n",
            time,
            evens,
            odds
        );
    }

    public static void display() {
        //System.out.printf("n = %d\n", n);
    }
}
