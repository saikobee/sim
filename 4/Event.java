import java.util.*;

public abstract class Event implements Comparable<Event> {
    private static PriorityQueue<Event> q = new PriorityQueue<Event>();

    protected static long curTime = 0;

    protected static final int WRITE_TIME = 10;

    public static void add(Event e) {
        q.add(e);
    }

    public static boolean isEmpty() { return q.isEmpty(); }

    public static void startSimulation() {
        while (! q.isEmpty()) {
            q.poll().simulate();
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
            inodeScheduleWriteCompleted(inode, buf);
        }
        else {
            queueToWrite(inode, buf);
        }
    }

    public static void blockSimulateStore(Block block, String data) {
        if (Disk.isIdle()) {
            blockScheduleWriteCompleted(block, data);
        }
        else {
            queueToWrite(block, data);
        }
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static void inodeScheduleWriteCompleted(Inode inode, StringBuffer buf) {
        add(new InodeWriteCompletedEvent(curTime + WRITE_TIME, inode, buf));
    }

    public static void blockScheduleWriteCompleted(Block block, String data) {
        add(new BlockWriteCompletedEvent(curTime + WRITE_TIME, block, data));
    }

    public static void queueToWrite(Inode inode, StringBuffer buf) {
        Disk.add(new InodeWriteCompletedEvent(0, inode, buf));
    }

    public static void queueToWrite(Block block, String data) {
        Disk.add(new BlockWriteCompletedEvent(0, block, data));
    }
}
