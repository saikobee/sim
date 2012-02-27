public class FileSystem {
    // NOTE: NUM_SECTORS may not exceed 2^16 under current implementation.
    protected final int NUM_SECTORS = 16;
    protected final int NUM_INODES  = 4;
    protected final int NUM_BLOCKS  = NUM_SECTORS - NUM_INODES;

    protected InodeList inodeFreeList;
    protected BlockList blockFreeList;
    protected FileList fileList;
    protected Sector[] sectors;

    public FileSystem() {
        fileList      = new FileList();
        sectors       = new Sector[NUM_SECTORS];
        inodeFreeList = new InodeList(sectors, NUM_INODES);
        blockFreeList = new BlockList(sectors, NUM_INODES, NUM_SECTORS);
    }

    public Sector getSector(int n) {
        return sectors[n];
    }

    protected Inode allocateInode() {
        if (inodeFreeList.isEmpty()) {
            throw new FileTooBig("All out of inodes!");
        }

        return inodeFreeList.remove(0);
    }

    protected Block allocateBlock() {
        if (blockFreeList.isEmpty()) {
            throw new FileTooBig("All out of blocks!");
        }

        return blockFreeList.remove(0);
    }

    protected Block allocateSingleIndirect() {
        Block block = allocateBlock();

        for (int i = 0; i < Inode.LINKS_PER_BLOCK; i++) {
            block.setBlockNumber(i, allocateBlock().getNumber());
        }

        return block;
    }

    protected String load(String name) {
        Inode inode = fileList.inodeForName(name);
        if (inode != null) {
            return inode.load();
        }

        return inode != null? inode.load(): "Bad filename: " + name;
    }

    protected void delete(String name) {
        File  file  = fileList.removeByName(name);
        Inode inode = file.getInode();
        delete(inode);
    }

    private void delete(File file) {
        Inode inode = file.getInode();
        delete(inode);
    }

    private void delete(Inode inode) {
        inodeFreeList.add(inode);

        Block zero = inode.getDirectLink();
        Block one  = inode.getSingleIndirectLink();
        Block two  = inode.getDoubleIndirectLink();

        blockFreeList.add(inode.getDirectLink());

        if (one != null) blockFreeList.add(inode.getSingleIndirectLink());
        if (two != null) blockFreeList.add(inode.getDoubleIndirectLink());

        inode.clear();
    }

    protected void nuke() {
        for (File file: fileList) {
            delete(file);
        }

        fileList.clear();
    }

    protected void save(String name, String contents) {
        Inode inode = allocateInode();
        inode.store(contents);
        fileList.add(new File(name, inode));
        // System.out.println(fileList);
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("FS:\n");
        result.append("Free inodes:" + inodeFreeList + "\n");
        result.append("Free blocks:" + blockFreeList + "\n");
        result.append("Files:"       + fileList      + "\n");

        return "" + result;
    }
}
