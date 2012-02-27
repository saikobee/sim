public class FileTooBig extends RuntimeException {
    public FileTooBig() {
        super("File too big!");
    }

    public FileTooBig(String msg) {
        super(msg);
    }
}
