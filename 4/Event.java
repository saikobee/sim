public abstract class Event implements Comparable {
    protected long time;
    protected long n;

    public void simulate() {
        Sim.time = time;
    }

    public int compareTo(Object obj) {
        Event that = (Event)obj;

        return (int)(this.time - that.time);
    }
}
