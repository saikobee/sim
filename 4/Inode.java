public class Inode extends Sector {
    protected int size;
    protected Block directLink;
    protected Block singleIndirectLink;
    protected Block doubleIndirectLink;

    protected static final int LINKS_PER_BLOCK = 4;

    public Inode(int number) {
        super(number);
    }

    public void store(StringBuffer buf) {
        if (buf.length() > doubleSizeMax())
            throw new FileTooBig();

        simulateStore(buf);
    }

    public void actuallyDoTheStore(StringBuffer buf) {
        size = buf.length();

        int x = Block.BLOCK_LENGTH;
        int y = singleSizeMax();
        int z = doubleSizeMax();

        if      (size <= x) storeDirect(buf);
        else if (size <= y) storeSingle(buf);
        else if (size <= z) storeDouble(buf);
        else throw new FileTooBig();
    }

    public void store(String data) {
        store(new StringBuffer(data));
    }

    public void simulateStore(StringBuffer buf) {
        Event.inodeSimulateStore(this, buf);
    }

    public String load() {
        StringBuffer result = new StringBuffer();

        result.append(loadDirect());

        if (size > Block.BLOCK_LENGTH) result.append(loadSingleIndirect());
        if (size > singleSizeMax())    result.append(loadDoubleIndirect());

        return "" + result;
    }

    protected String loadDirect()         { return directLink.loadDirect(); }
    protected String loadSingleIndirect() { return singleIndirectLink.loadSingleIndirect(); }
    protected String loadDoubleIndirect() { return doubleIndirectLink.loadDoubleIndirect(); }

    public Block getDirectLink()         { return directLink; }
    public Block getSingleIndirectLink() { return singleIndirectLink; }
    public Block getDoubleIndirectLink() { return doubleIndirectLink; }

    public void clear() {
        size = 0;
        directLink         = null;
        singleIndirectLink = null;
        doubleIndirectLink = null;
    }

    private void consume(StringBuffer buf) {
        final int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        final int length = Math.min(buf.length(), BLOCK_LENGTH);
        buf.delete(0, length);
    }

    protected void storeDirect(StringBuffer buf) {
        if (directLink == null)
            directLink = Globals.fs.allocateBlock();

        //directLink.store(buf);
        Event.blockSimulateStore(directLink, buf);
        consume(buf);
    }

    protected void storeSingle(StringBuffer data) {
        singleIndirectLink = Globals.fs.allocateBlock();

        storeDirect(data);

        StringBuffer copy = new StringBuffer(data.toString());
        Block[] linkers = new Block[LINKS_PER_BLOCK];
        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            if (copy.length() > 0) {
                Block b = Globals.fs.allocateBlock();
                linkers[i] = b;
                consume(copy);
            }
            else {
                linkers[i] = null;
            }
        }

        Event.blockSimulateStore(singleIndirectLink, linkers);

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            if (data.length() > 0) {
                if (linkers[i] != null) {
                    Debug.printf("??? THIS IS THE DATA :| %s\n", data);
                    Event.blockSimulateStore(linkers[i], data);
                    consume(data);
                }
            }
        }
    }

    protected void storeDouble(StringBuffer data) {
        storeSingle(data);

        if (data.length() <= 0)
            return;

        doubleIndirectLink = Globals.fs.allocateBlock();

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            Block single = null;
            if (data.length() > 0) {
                single = Globals.fs.allocateBlock();
                doubleIndirectLink.setBlock(i, single);

                for (int j = 0; j < LINKS_PER_BLOCK; j++) {
                    if (data.length() > 0) {
                        Block direct = Globals.fs.allocateBlock();
                        single.setBlock(j, direct);

                        direct.store(data);
                        consume(data);
                    }
                    else if (single != null) {
                        single.setBlock(j, null);
                    }
                }
            }
            else {
                doubleIndirectLink.setBlock(i, null);
            }
        }
    }

    protected int singleSizeMax() {
        return Block.BLOCK_LENGTH + LINKS_PER_BLOCK * Block.BLOCK_LENGTH;
    }

    protected int doubleSizeMax() {
        return singleSizeMax() + LINKS_PER_BLOCK * LINKS_PER_BLOCK * Block.BLOCK_LENGTH;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        Block one = singleIndirectLink;
        Block two = doubleIndirectLink;

        result.append("\nInode:\n");
        result.append("\tnumber="    + number + "\n");
        result.append("\tsize="      + size + "\n");
        result.append("\tdirect="    + directLink);
        result.append("\n\tindirect="  + (one == null? null: one.getBlocks()));
        result.append("\n\tindirect²=" + (two == null? null: two.getDoubleBlocks()));

        return "" + result;
    }
}
