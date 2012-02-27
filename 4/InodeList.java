import java.util.ArrayList;

public class InodeList extends ArrayList<Inode> {
    public InodeList(Sector[] sectors, int n) {
        for (int i = 0; i < n; i++) {
            Inode inode = new Inode(i);

            sectors[i] = inode;
            add(inode);
        }
    }
}
