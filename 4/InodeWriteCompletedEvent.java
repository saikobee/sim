import java.util.*;

public class InodeWriteCompletedEvent extends Event {
    private Inode        inode;
    private StringBuffer buf;
    private long diff;

    public InodeWriteCompletedEvent(long time, Inode inode, StringBuffer buf) {
        super(time);

        this.inode = inode;
        this.buf   = buf;
    }

    public void simulate() {
        Debug.printf("*** INODE WRITE COMPLETING\n");
        inode.actuallyDoTheStore(buf);
        if (Disk.emptyQ()) {
            Disk.setIdle(true);
        }
        else {
            Event e = Disk.poll();

            diff = Event.curTime - e.getTime();
            e.setTime(Event.curTime + Event.WRITE_TIME);

            Debug.printf(">>> WAITED %d msec on the disk queue\n", diff);

            Event.add(e);
        }
    }
}
