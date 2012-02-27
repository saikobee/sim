public class Block extends Sector {
    protected static final int BLOCK_LENGTH = 8;
    protected byte[] bytes;

    public Block(int n) {
        super(n);
        bytes = new byte[BLOCK_LENGTH];
    }

    public void store(String s) {
        super.store();

        if (s.length() > BLOCK_LENGTH) {
            throw new FileTooBig();
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                bytes[i] = (byte) s.charAt(i);
            }
        }
    }

    public String loadDirect() {
        super.doLoad();

        StringBuffer result = new StringBuffer();

        int i = 0;
        while (i < BLOCK_LENGTH && bytes[i] != 0) {
            result.append((char) bytes[i++]);
        }

        return "" + result;
    }

    public String loadSingleIndirect() {
        return "<<SINGLE INDIRECT>>";
    }

    public String loadDoubleIndirect() {
        return "<<DOUBLE INDIRECT>>";
    }

    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("Block:");
        result.append("\tnumber=" + getNumber());
        result.append("\tbytes="  + loadDirect());

        return "" + result;
    }
}
