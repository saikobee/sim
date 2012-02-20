public class EvenEvent extends Event {
    public EvenEvent(long time, int n) {
        this.time = time;
        this.n    = n;
    }

    public void simulate() {
        Sim.incEvens();
        Sim.setN(n);
        Sim.time = time;
        if (n > 1) {
            Sim.add(Sim.makeEvent(time + 1, n / 2));
            Sim.add(Sim.makeEvent(time + 1, n / 2));
        }
    }
}
