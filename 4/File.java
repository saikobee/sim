public class File {
    protected String name;
    protected Inode  inode;

    public File(String name, Inode inode) {
        this.name  = name;
        this.inode = inode;
    }

    public String getName()  { return name;  }
    public Inode  getInode() { return inode; }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("File:");
        result.append("\tname="  + name);
        result.append("\tinode=" + inode);

        return "" + result;
    }
}
