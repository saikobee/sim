import java.util.*;

public class BlockWriteCompletedEvent extends Event {
    private Block  block;
    private String data;
    private long diff;
    private Block[] linkers;
    private boolean isLinky;

    public BlockWriteCompletedEvent(long time, Block block, String data) {
        super(time);

        this.block   = block;
        this.data    = data;
        this.linkers = null;
        this.isLinky = false;
    }

    public BlockWriteCompletedEvent(long time, Block block, Block[] linkers) {
        super(time);

        this.block   = block;
        this.data    = null;
        this.linkers = linkers;
        this.isLinky = true;
    }

    public void simulate() {
        Debug.printf("*** BLOCK WRITE COMPLETING\n");
        getTheStorageDonePlease();
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

    public void getTheStorageDonePlease() {
        if (! isLinky) {
            block.actuallyDoTheStore(data);
        }
        else {
            for (int i = 0; i < linkers.length; i++) {
                block.setBlock(i, linkers[i]);
            }
        }
    }

    public String toString() {
        return super.toString()
            + (!isLinky
                ? " data=" + data.substring(0, Math.min(data.length(), Block.BLOCK_LENGTH))
                : " WITH LINKS");
    }
}
