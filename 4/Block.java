import java.util.*;

public class Block extends Sector implements Comparable<Block> {
    protected static final int BLOCK_LENGTH = 8;
    protected byte[] bytes;

    public Block(int n) {
        super(n);
        bytes = new byte[BLOCK_LENGTH];
    }

    public void store(String data) {
        simulateStore(data);
    }

    public void simulateStore(String data) {
        Event.blockSimulateStore(this, data);
    }

    public void actuallyDoTheStore(String s) {
        int len = Math.min(s.length(), BLOCK_LENGTH);
        for (int i = 0; i < len; i++) {
            Debug.printf("Storing '%c'\n", s.charAt(i));
            bytes[i] = (byte) s.charAt(i);
        }

        Debug.printf("Stored: \"");
        for (byte b: bytes) {
            Debug.printf("%c", (char)b);
        }
        Debug.printf("\"\n");
    }

    public void store(StringBuffer buf) {
        store("" + buf);
    }

    public int getBlockNumber(int i) {
        return (bytes[i + i] << 8) | bytes[i + i + 1];
    }

    public void setBlockNumber(int i, int num) {
        bytes[i + i    ] = (byte)((num >> 8) & 0xff);
        bytes[i + i + 1] = (byte)((num >> 0) & 0xff);
    }

    public Block getBlock(int i) {
        int n = getBlockNumber(i);
        return n == 0? null: (Block)Globals.fs.getSector(n);
    }

    public void setBlock(int i, Block b) {
        setBlockNumber(i, b == null? 0: b.getNumber());
    }

    public List<Block> getBlocks() {
        List<Block> result = new ArrayList<Block>();

        result.add(this);

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            Block b = getBlock(i);

            if (b != null) {
                result.add(b);
            }
        }

        return result;
    }

    public List<Block> getDoubleBlocks() {
        List<Block> result = new ArrayList<Block>();
        List<Block> links  = getBlocks();

        result.add(this);

        for (Block link: links) {
            //result.add(link);
            for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
                Block b = link.getBlock(i);

                if (b != null) {
                    result.add(b);
                }
            }
        }

        return result;
    }

    public String loadDirect() {
        StringBuffer result = new StringBuffer();

        int i = 0;
        while (i < BLOCK_LENGTH && bytes[i] != 0) {
            result.append((char)bytes[i++]);
        }

        return "" + result;
    }

    public String loadSingleIndirect() {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            Block block = getBlock(i);

            if (block != null)
                result.append(block.loadDirect());
        }

        return "" + result;
    }

    public String loadDoubleIndirect() {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            Block block = getBlock(i);

            if (block != null)
                result.append(block.loadSingleIndirect());
        }

        return "" + result;
    }

    public int compareTo(Block that) {
        return this.number - that.number;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("\nBlock:");
        result.append("\tnumber="   + getNumber());
        result.append("\tbytes=\""  + loadDirect() + "\"");
        result.append("");

        return "" + result;
    }
}
