public class OddEvent extends Event {
    public OddEvent(long time, long n) {
        this.time = time;
        this.n    = n;
    }

    public void simulate() {
        Sim.odds++;
        Sim.time = time;
        if (n > 1) {
            Sim.add(new EvenEvent(time + 10000, 3*n + 1));
        }
    }
}
