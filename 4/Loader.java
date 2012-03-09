import java.util.*;
import java.io.*;

public class Loader {
    public void load(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            parse(new Scanner(file));
        }
        catch (FileNotFoundException e) {
            Debug.printf("Looks like that file didn't exist: %s\n", filename);
        }
    }

    public void parse(Scanner in) {
        while (in.hasNextLine()) {
            String[] words = in.nextLine().split("\\s+");

            String cmd  = words[0];
            String disk = words[1];
            String time = words[2];
            String text = words[3];

            String[] timeChunks = time.split(":");

            int hours = new Integer(timeChunks[0]);
            int mins  = new Integer(timeChunks[1]);
            int secs  = new Integer(timeChunks[2]);
            int msecs = new Integer(timeChunks[3]);

            int diskno = new Integer(disk);

            Debug.printf(
                "time=%02d:%02d:%02d:%03d disk=%01d cmd=%-6s text=%s\n",
                hours, mins, secs, msecs,
                diskno,
                cmd,
                text
            );
        }
    }

    public static void main(String... args) {
        new Loader().load("test.txt");
    }
}
