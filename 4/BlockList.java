import java.util.ArrayList;
import java.util.Collections;

public class BlockList extends ArrayList<Block> {
    public BlockList(Sector[] theSectors, int nInode, int nBlocks) {
        for (int i = nInode; i < nBlocks; i++) {
            Block block = new Block(i);

            theSectors[i] = block;
            add(block);
        }
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        Collections.sort(this);

        result.append("BlockList:");
        result.append("\tsize=" + size());
        result.append("\tlist=" + super.toString());

        return "" + result;
    }
}
