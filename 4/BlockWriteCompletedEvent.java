import java.util.*;

public class BlockWriteCompletedEvent extends Event {
    private Block  block;
    private String data;

    public BlockWriteCompletedEvent(long time, Block block, String data) {
        super(time);

        this.block = block;
        this.data  = data;
    }

    public void simulate() {
        Debug.printf("***BLOCK WRITE COMPLETING***\n");
        block.actuallyDoTheStore(data);
        if (! Disk.isIdle()) {
            Event e = Disk.poll();
            e.setTime(Event.curTime + Event.WRITE_TIME);
            Event.add(e);
        }
    }
}
