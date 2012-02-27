public class Inode extends Sector {
    protected int size;
    protected Block directLink;
    protected Block singleIndirectLink;
    protected Block doubleIndirectLink;

    protected static final int LINKS_PER_BLOCK = 4;

    public Inode(int number) {
        super(number);
    }

    public void store(String data) {
        super.store();

        size = data.length();

        int x = Block.BLOCK_LENGTH;
        int y = singleSizeMax();
        int z = doubleSizeMax();

        if      (size <= x) storeDirect(data);
        else if (size <= y) storeSingle(data);
        else if (size <= z) storeDouble(data);
        else throw new FileTooBig();
    }

    public String load() {
        super.doLoad();
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

    protected void storeDirect(String data) {
        if (directLink == null)
            directLink = Globals.fs.allocateBlock();

        directLink.store(data);
    }

    protected void storeSingle(String data) {
        singleIndirectLink = Globals.fs.allocateBlock();

        final int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        storeDirect(data);

        data = data.substring(Math.min(data.length(), BLOCK_LENGTH));

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            Block b = Globals.fs.allocateBlock();
            singleIndirectLink.setBlock(i, b);

            if (data.length() > 0) {
                b.store(data);
                data = data.substring(Math.min(data.length(), BLOCK_LENGTH));
            }
        }
    }

    protected void storeDouble(String data) {
        int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        int SINGLE_SIZE  = LINKS_PER_BLOCK * BLOCK_LENGTH;
        int size = data.length();

        storeSingle(data);
        data = data.substring(Math.min(singleSizeMax(), data.length()));

        doubleIndirectLink = Globals.fs.allocateBlock();

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            Block single = Globals.fs.allocateBlock();
            doubleIndirectLink.setBlock(i, single);
            for (int j = 0; j < LINKS_PER_BLOCK; j++) {
                Block direct = Globals.fs.allocateBlock();
                single.setBlock(j, direct);

                if (data.length() > 0) {
                    direct.store(data);
                    data = data.substring(Math.min(BLOCK_LENGTH, data.length()));
                }
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
