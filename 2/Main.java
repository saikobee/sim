import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataList dl = new DataList(Arrays.asList(
            new DataPoint( 8, 20),
            new DataPoint(16,  8),
            new DataPoint(24,  9),
            new DataPoint(32, 50),
            new DataPoint(40,  8),
            new DataPoint(48, 20)
        ));

        new Grapher(dl);
    }
}
