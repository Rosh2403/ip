import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the given file path.
     *
     * @param filePath path to the file used for saving and loading tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws IOException if there is an error reading the file
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" \\| ");
            if (parts[0].equals("T")) {
                tasks.add(new Todo(parts[2]));
            } else if (parts[0].equals("D")) {
                tasks.add(new Deadline(parts[2], parts[3]));
            } else if (parts[0].equals("E")) {
                tasks.add(new Event(parts[2], parts[3], parts[4]));
            }
            if (parts[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the list of tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        String text = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Todo) {
                text += "T | " + (tasks.get(i).isDone ? "1" : "0") + " | " + tasks.get(i).description + "\n";
            } else if (tasks.get(i) instanceof Deadline) {
                Deadline d = (Deadline) tasks.get(i);
                text += "D | " + (tasks.get(i).isDone ? "1" : "0") + " | " + tasks.get(i).description + " | " + d.getByDate() + "\n";
            } else if (tasks.get(i) instanceof Event) {
                Event e = (Event) tasks.get(i);
                text += "E | " + (tasks.get(i).isDone ? "1" : "0") + " | " + tasks.get(i).description + " | " + e.getFromWhen() + " | " + e.getToWhen() + "\n";
            }
        }
        new File(filePath).getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }
}