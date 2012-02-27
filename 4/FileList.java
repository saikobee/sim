import java.util.*;

public class FileList extends ArrayList<File> {
    public Inode inodeForName(String name) {
        for (File file: this) {
            if (file.getName().equals(name)) {
                return file.getInode();
            }
        }

        return null;
    }
}
