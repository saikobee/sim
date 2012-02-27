import java.util.*;

public class Main {
    private static int numNs = 1000000;
    public static void main(String[] args) {
        long maxTime = Long.MIN_VALUE;
        long maxN    = Integer.MIN_VALUE;
        for (int i = 0; i < numNs; i++) {
            //System.out.printf("n = %d\n", i);
            //System.out.printf("#############\n");
            new Go(i);
            if (Sim.time > maxTime) {
                maxTime = Sim.time;
                maxN    = i;
            }
            //System.out.println();
        }

        System.out.printf("Maxmimum time\n");
        System.out.printf("=============\n");
        System.out.printf("time = %d\n", maxTime);
        System.out.printf("n    = %d\n", maxN);
    }
}
