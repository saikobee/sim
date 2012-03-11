public class Main {
    public static void main(String... args) {
        Globals.init();
        new Loader().load("test.txt");
        Event.startSimulation();
        new Editor();
    }
}
