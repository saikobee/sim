public class EvenEvent extends Event {
    public EvenEvent(long time, long n) {
        this.time = time;
        this.n    = n;
    }

    public void simulate() {
        Sim.evens++;
        Sim.time = time;
        if (n > 1) {
            Sim.add(Sim.makeEvent(time + 1, n / 2));
        }
    }
}
