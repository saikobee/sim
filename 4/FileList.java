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

    public File removeByName(String name) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getName().equals(name)) {
                return this.remove(i);
            }
        }

        return null;
    }
}
