import java.util.*;

public class Block extends Sector {
    protected static final int BLOCK_LENGTH = 8;
    protected byte[] bytes;

    public Block(int n) {
        super(n);
        bytes = new byte[BLOCK_LENGTH];
    }

    public void store(String s) {
        super.store();

        if (s.length() > BLOCK_LENGTH) {
            throw new FileTooBig();
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                Debug.printf("Storing '%c'\n", s.charAt(i));
                bytes[i] = (byte) s.charAt(i);
            }
        }
        Debug.printf("Stored: \"");
        for (byte b: bytes) {
            Debug.printf("%c", (char)b);
        }
        Debug.printf("\"\n");
    }

    public int getBlockNumber(int i) {
        return (bytes[i + i] << 8) | bytes[i + i + 1];
    }

    public void setBlockNumber(int i, int num) {
        bytes[i + i    ] = (byte)((num >> 8) & 0xff);
        bytes[i + i + 1] = (byte)((num >> 0) & 0xff);
    }

    public List<Block> getBlocks() {
        List<Block> result = new ArrayList<Block>();

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            result.add((Block)Globals.fs.getSector(getBlockNumber(i)));
        }

        return result;
    }

    public String loadDirect() {
        super.doLoad();

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
            Block block = (Block)Globals.fs.getSector(getBlockNumber(i));

            result.append(block.loadDirect());
        }

        return "" + result;
    }

    public String loadDoubleIndirect() {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            Block block = (Block)Globals.fs.getSector(getBlockNumber(i));

            result.append(block.loadSingleIndirect());
        }

        return "" + result;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("\nBlock:\n");
        result.append("\tnumber=" + getNumber()  + "\n");
        result.append("\tbytes="  + loadDirect() + "\n");
        result.append("\n");

        return "" + result;
    }
}
