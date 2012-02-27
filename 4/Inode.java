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
        int z = Block.BLOCK_LENGTH;

        if (data.length() > singleSizeMax())
            throw new FileTooBig();

        singleIndirectLink = Globals.fs.allocateSingleIndirect();

        Block b1 = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(0));
        Block b2 = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(1));
        Block b3 = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(2));
        Block b4 = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(3));

        storeDirect(data.substring(0, z));

        if (size >= 1*z) b1.store(data.substring(1*z, Math.min(2*z, size)));
        if (size >= 2*z) b2.store(data.substring(2*z, Math.min(3*z, size)));
        if (size >= 3*z) b3.store(data.substring(3*z, Math.min(4*z, size)));
        if (size >= 4*z) b4.store(data.substring(4*z, Math.min(5*z, size)));
    }

    protected void storeDouble(String data) {
        throw new UnsupportedOperationException();
    }

    protected int singleSizeMax() {
        return Block.BLOCK_LENGTH + LINKS_PER_BLOCK + Block.BLOCK_LENGTH;
    }

    protected int doubleSizeMax() {
        return singleSizeMax() + LINKS_PER_BLOCK * LINKS_PER_BLOCK * Block.BLOCK_LENGTH;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        Block one = singleIndirectLink;

        result.append("\nInode:\n");
        result.append("\tnumber="    + number + "\n");
        result.append("\tsize="      + size + "\n");
        result.append("\tdirect="    + directLink);
        result.append("\tindirect="  + (one == null? null: one.getBlocks()));
        result.append("\tindirect²=" + doubleIndirectLink);

        return "" + result;
    }
}
