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
            new DataPoint( 5,  8),
            new DataPoint(10,  9),
            new DataPoint( 3, 50),
            new DataPoint( 5,  8),
            new DataPoint(10, 20),
            new DataPoint( 5,  8),
            new DataPoint(10,  9),
            new DataPoint( 3, 50),
            new DataPoint( 5,  8)
        ));

        new Grapher(dl);
    }
}
