import java.util.*;

public class InodeWriteCompletedEvent extends Event {
    private Inode        inode;
    private StringBuffer buf;

    public InodeWriteCompletedEvent(long time, Inode inode, StringBuffer buf) {
        super(time);

        this.inode = inode;
        this.buf   = buf;
    }

    public void simulate() {
        Debug.printf("***INODE WRITE COMPLETING***\n");
        inode.actuallyDoTheStore(buf);
        if (! Disk.isIdle()) {
            Event e = Disk.poll();
            e.setTime(Event.curTime + Event.WRITE_TIME);
            Event.add(e);
        }
    }
}
