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

    protected void storeDirect(String data) {
        directLink = Globals.fs.allocateBlock();
        int idx = Math.min(Block.BLOCK_LENGTH, data.length());
        directLink.store(data.substring(0, idx));
    }

    protected void storeSingle(String data) {
        throw new UnsupportedOperationException();
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

        result.append("Inode:");
        result.append("\tnumber="      + number);
        result.append("\tsize="        + size);
        result.append("\n\tdirect="    + directLink);
        result.append("\n\tindirect="  + singleIndirectLink);
        result.append("\n\tindirect²=" + doubleIndirectLink);

        return "" + result;
    }
}
