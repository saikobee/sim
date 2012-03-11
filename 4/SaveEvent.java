public class SaveEvent extends Event {
    private final String file;
    private final String text;

    public SaveEvent(long time, String file, String text) {
        super(time);

        this.file = file;
        this.text = text;
    }

    public void simulate() {
        Debug.printf("%s\n", this);
    }

    public String toString() {
        return String.format(
            "SAVE time=%s file=%s text=%s",
            Loader.timeLongToString(time),
            file, text
        );
    }
}
