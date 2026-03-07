import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Roshy {
    private static final String LOGO = "_____\n" + "|  \\/  | __ _ _ __(_) ___  \n" + "| |\\/| |/ _` | '__| |/ _ \\ \n" + "| |  | | (_| | |  | | (_) |\n" + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final int DELETE_PREFIX_LENGTH = 7;
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int MARK_PREFIX_LENGTH = 5;
    private static final int BY_SLASH_OFFSET = 4;
    private static final int FROM_SLASH_OFFSET = 6;
    private static final int TO_SLASH_OFFSET = 4;

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Roshy roshy = new Roshy();
        roshy.run();
    }

    public void run() throws IOException {
        loadTasks();
        System.out.println(LOGO + " Hello I'm Roshy\n What can I do for you?\n");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            processCommand(line);
        }
        System.out.println(BYE_MESSAGE);
    }

    private void printList() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet!");
            return;
        }
        System.out.println("Here are the tasks in your list\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i).toString());
        }
    }

    private void processCommand(String line) {
        try {
            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("mark")) {
                handleMark(line);
            } else if (line.startsWith("todo")) {
                handleTodo(line);
            } else if (line.startsWith("deadline")) {
                handleDeadline(line);
            } else if (line.startsWith("event")) {
                handleEvent(line);
            } else if (line.startsWith("delete")) {
                handleDelete(line);
            } else {
                System.out.println("I don't understand what you mean");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void handleMark(String line) throws IOException {
        if (hasNoDescription(line, "mark")) {
            return;
        }
        int taskNum = Integer.parseInt(line.substring(MARK_PREFIX_LENGTH)) - 1;
        tasks.get(taskNum).markAsDone();
        System.out.println("Nice I've marked this as done!\n" + tasks.get(taskNum).toString());
        saveTasks();
    }

    private void handleTodo(String line) throws IOException {
        if (hasNoDescription(line, "todo")) {
            return;
        }
        tasks.add(new Todo(line.substring(TODO_PREFIX_LENGTH)));
        System.out.println(tasks.get(tasks.size() - 1).toString());
        saveTasks();
    }

    private void handleDeadline(String line) throws IOException {
        if (hasNoDescription(line, "deadline")) {
            return;
        }
        int slashIndex = line.indexOf("/");
        String byDate = line.substring(slashIndex + BY_SLASH_OFFSET).trim();
        String description = line.substring(DEADLINE_PREFIX_LENGTH, slashIndex).trim();
        tasks.add(new Deadline(description, byDate));
        System.out.println(tasks.get(tasks.size() - 1).toString());
        saveTasks();
    }

    private void handleEvent(String line) throws IOException {
        if (hasNoDescription(line, "event")) {
            return;
        }
        int slashIndex1 = line.indexOf("/");
        int slashIndex2 = line.indexOf("/", slashIndex1 + 1);
        String fromWhen = line.substring(slashIndex1 + FROM_SLASH_OFFSET, slashIndex2).trim();
        String toWhen = line.substring(slashIndex2 + TO_SLASH_OFFSET).trim();
        String description = line.substring(EVENT_PREFIX_LENGTH, slashIndex1).trim();
        tasks.add(new Event(description, toWhen, fromWhen));
        System.out.println(tasks.get(tasks.size() - 1).toString());
        saveTasks();
    }

    private boolean hasNoDescription(String line, String command) {
        if (line.trim().equals(command)) {
            System.out.println("You need a description for your " + command);
            return true;
        }
        return false;
    }

    private void handleDelete(String line) throws IOException {
        int index = Integer.parseInt(line.substring(DELETE_PREFIX_LENGTH)) - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I have removed this task\n" + deletedTask + "\nNow you have " + tasks.size() + " tasks in the list");
        saveTasks();
    }

    private void saveTasks() throws IOException {
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
        writeToFile("./data/roshy.txt", text);
    }

    private void writeToFile(String filePath, String text) throws IOException {
        new File(filePath).getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    private void loadTasks() throws IOException {
        File file = new File("./data/roshy.txt");
        if (!file.exists()) {
            return;
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
    }
}