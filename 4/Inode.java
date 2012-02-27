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
        if (data.length() > singleSizeMax())
            throw new FileTooBig();

        singleIndirectLink = Globals.fs.allocateSingleIndirect();

        final int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        storeDirect(data);

        data = data.substring(Math.min(data.length(), BLOCK_LENGTH));

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            Block b = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(i));

            if (data.length() > 0) {
                b.store(data);
                Debug.printf("BLOCK LENGTH LOL %d\n", BLOCK_LENGTH);
                data = data.substring(Math.min(data.length(), BLOCK_LENGTH));
            }
        }
    }

    protected void storeDouble(String data) {
        if (data.length() > doubleSizeMax())
            throw new FileTooBig();

        int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        int SINGLE_SIZE  = LINKS_PER_BLOCK * BLOCK_LENGTH;
        int size = data.length();

        storeSingle(data);

        doubleIndirectLink = Globals.fs.allocateDoubleIndirect();

        for (int i = 0; i < LINKS_PER_BLOCK; i++) {
            Block single = doubleIndirectLink.getBlock(i);
            for (int j = 0; j < LINKS_PER_BLOCK; j++) {
                Block direct = single.getBlock(j);
                direct.store(data.substring(i*BLOCK_LENGTH + j*SINGLE_SIZE));
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
        result.append("\tindirect="  + (one == null? null: one.getBlocks()));
        result.append("\tindirect²=" + (two == null? null: two.getBlocks()));

        return "" + result;
    }
}
