import java.util.*;

public abstract class Event implements Comparable<Event> {
    private static PriorityQueue<Event> q = new PriorityQueue<Event>();

    public static void add(Event e) {
        q.add(e);
    }

    public static void startSimulation() {
        while (! q.isEmpty()) {
            q.poll().simulate();
        }
    }

    protected final long time;

    abstract public void simulate();

    public Event(long time) {
        this.time = time;
    }

    public int compareTo(Event that) {
        return (int)(this.time - that.time);
    }
}
