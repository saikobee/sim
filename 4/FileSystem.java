public class FileSystem {
    protected final int NUM_SECTORS = 8;
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

    protected String load(String name) {
        Inode inode = fileList.inodeForName(name);
        if (inode != null) {
            return inode.load();
        }

        return inode != null? inode.load(): "Bad filename: " + name;
    }

    protected void save(String name, String contents) {
        Inode inode = allocateInode();
        inode.store(contents);
        fileList.add(new File(name, inode));
        // System.out.println(fileList);
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("FS:");
        result.append("\nFree inodes: " + inodeFreeList);
        result.append("\nFree blocks: " + blockFreeList);
        result.append("\nFiles: " + fileList);

        return "" + result;
    }
}
