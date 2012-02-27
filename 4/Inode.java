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

        int BLOCK_LENGTH = Block.BLOCK_LENGTH;
        int size = data.length();
        storeDirect(data.substring(0, BLOCK_LENGTH));

        for (int i = 1; i <= LINKS_PER_BLOCK; i++) {
            Block b = (Block)Globals.fs.getSector(singleIndirectLink.getBlockNumber(i - 1));

            if (size >= i*BLOCK_LENGTH) {
                b.store(data.substring(i*BLOCK_LENGTH, Math.min((i+1)*BLOCK_LENGTH, size)));
            }
        }
    }

    private void storeSingle(Block singleIndirectLink, String data) {
    }

    protected void storeDouble(String data) {
        if (data.length() > doubleSizeMax())
            throw new FileTooBig();

        int z = Block.BLOCK_LENGTH;
        int q = singleSizeMax() - z;
        int size = data.length();

        storeSingle(data.substring(0, z+q));

        doubleIndirectLink = Globals.fs.allocateDoubleIndirect();

        Block b1 = (Block)Globals.fs.getSector(doubleIndirectLink.getBlockNumber(0));
        Block b2 = (Block)Globals.fs.getSector(doubleIndirectLink.getBlockNumber(1));
        Block b3 = (Block)Globals.fs.getSector(doubleIndirectLink.getBlockNumber(2));
        Block b4 = (Block)Globals.fs.getSector(doubleIndirectLink.getBlockNumber(3));

        if (size >= z+1*q) storeSingle(b1, data.substring(z+1*q, Math.min(z+2*q, size)));
        if (size >= z+2*q) storeSingle(b2, data.substring(z+2*q, Math.min(z+3*q, size)));
        if (size >= z+3*q) storeSingle(b3, data.substring(z+3*q, Math.min(z+4*q, size)));
        if (size >= z+4*q) storeSingle(b4, data.substring(z+4*q, Math.min(z+5*q, size)));
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
