import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

// SaveManager handles saving and loading game data to a file
public class SaveManager {

    // Saves game data to a text file
    public static void save(int level, int score, int lives, int time) {
        try {
            // Create or overwrite save.txt
            PrintWriter writer = new PrintWriter(new File("save.txt"));

            // Write each value on a new line
            writer.println(level);   // current level
            writer.println(score);   // player score
            writer.println(lives);   // remaining lives
            writer.println(time);    // time left

            // Close file to ensure data is written
            writer.close();

        } catch (Exception e) {
            // Print error if something goes wrong
            e.printStackTrace();
        }
    }

    // Loads game data from file and returns it as an array
    public static int[] load() {
        try {
            // Open the save file
            Scanner scanner = new Scanner(new File("save.txt"));

            // Create array to store values
            int[] data = new int[4];

            // Read each line and convert to integer
            data[0] = Integer.parseInt(scanner.nextLine()); // level
            data[1] = Integer.parseInt(scanner.nextLine()); // score
            data[2] = Integer.parseInt(scanner.nextLine()); // lives
            data[3] = Integer.parseInt(scanner.nextLine()); // time

            // Close file
            scanner.close();

            return data;

        } catch (Exception e) {
            // If file not found or error occurs
            e.printStackTrace();
            return null;
        }
    }
}