public class Go {
    public Go(int n) {
        initialize(n);
        while (! Sim.empty()) {
            Sim.event().simulate();
            //Sim.display();
        }
        //Sim.stats();
    }

    private void initialize(int n) {
        Sim.add(Sim.makeEvent(0, n));
    }
}
