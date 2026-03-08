import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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