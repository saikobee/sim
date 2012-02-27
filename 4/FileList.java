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

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("FileList:");
        result.append("\tsize=" + size());
        result.append("\tlist=" + super.toString());

        return "" + result;
    }
}
