import java.util.*;

public abstract class Event implements Comparable<Event> {
    private static PriorityQueue<Event> q = new PriorityQueue<Event>();

    protected static long curTime = 0;

    protected static final int WRITE_TIME = 10;

    public static void add(Event e) {
        q.add(e);
    }

    public static boolean isEmpty() { return q.isEmpty(); }
    public static int     size()    { return q.size();    }

    public static String stringy() { return "[\n\t" + Util.join(q, ",\n\t") + "\n]"; };

    public static void startSimulation() {
        while (! q.isEmpty()) {
            Debug.printf("EVENT Q = %s\n", Event.stringy());
            Debug.printf("DISK  Q = %s\n", Disk .stringy());
            Event e = q.poll();
            curTime = e.getTime();
            e.simulate();
        }
    }

    protected long time;

    abstract public void simulate();

    public Event(long time) {
        this.time = time;
    }

    public int compareTo(Event that) {
        return (int)(this.time - that.time);
    }

    public static void inodeSimulateStore(Inode inode, StringBuffer buf) {
        if (Disk.isIdle()) {
            Disk.setIdle(false);
            inodeScheduleWriteCompleted(inode, buf);
        }
        else {
            queueToWrite(inode, buf);
        }
    }

    public static void blockSimulateStore(Block block, String data) {
        if (Disk.isIdle()) {
            Disk.setIdle(false);
            blockScheduleWriteCompleted(block, data);
        }
        else {
            queueToWrite(block, data);
        }
    }

    public static void blockSimulateStore(Block block, Block[] linkers) {
        if (Disk.isIdle()) {
            Disk.setIdle(false);
            blockScheduleWriteCompleted(block, linkers);
        }
        else {
            queueToWrite(block, linkers);
        }
    }

    public static void blockSimulateStore(Block block, StringBuffer buffy) {
        blockSimulateStore(block, "" + buffy);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() { return time; }

    public String toString() {
        return getClass().getName() + "@" + Loader.timeLongToString(time);
    }

    public static void inodeScheduleWriteCompleted(Inode inode, StringBuffer buf) {
        add(new InodeWriteCompletedEvent(curTime + WRITE_TIME, inode, buf));
    }

    public static void blockScheduleWriteCompleted(Block block, String data) {
        add(new BlockWriteCompletedEvent(curTime + WRITE_TIME, block, data));
    }

    public static void blockScheduleWriteCompleted(Block block, Block[] linkers) {
        add(new BlockWriteCompletedEvent(curTime + WRITE_TIME, block, linkers));
    }

    public static void queueToWrite(Inode inode, StringBuffer buf) {
        Disk.add(new InodeWriteCompletedEvent(curTime, inode, buf));
    }

    public static void queueToWrite(Block block, String data) {
        Disk.add(new BlockWriteCompletedEvent(curTime, block, data));
    }
    public static void queueToWrite(Block block, Block[] linkers) {
        Disk.add(new BlockWriteCompletedEvent(curTime, block, linkers));
    }
}
