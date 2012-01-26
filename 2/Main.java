import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataList dl = new DataList(Arrays.asList(
            new DataPoint(10, 20),
            new DataPoint( 5,  8),
            new DataPoint(10,  9),
            new DataPoint( 3, 50),
            new DataPoint( 5,  8),
            new DataPoint(10, 20),
            new DataPoint(10,  2),
            new DataPoint(10, 20),
            new DataPoint(10, 40),
            new DataPoint( 6, 20),
            new DataPoint(10,  2),
            new DataPoint(10, 20),
            new DataPoint(10,  4),
            new DataPoint( 6, 20),
            new DataPoint( 2, 40),
            new DataPoint( 3, 20)
        ));

        new Grapher(dl);
    }
}
