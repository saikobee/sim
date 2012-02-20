public class Go {
    public Go() {
        initialize();
        while (! Sim.empty()) {
            Sim.event().simulate();
            Sim.display();
        }
        Sim.stats();
    }

    private void initialize() {
        final long time = 0;
        final int  n    = 3;
        Sim.add(Sim.makeEvent(time, n));
    }
}
