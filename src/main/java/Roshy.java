import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Roshy {
    private static final String LOGO = "_____\n" + "|  \\/  | __ _ _ __(_) ___  \n" + "| |\\/| |/ _` | '__| |/ _ \\ \n" + "| |  | | (_| | |  | | (_) |\n" + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final int MAX_TASKS = 100;
    private static final int TODO_PREFIX_LENGTH = 5;       // "todo "
    private static final int DEADLINE_PREFIX_LENGTH = 9;   // "deadline "
    private static final int EVENT_PREFIX_LENGTH = 6;      // "event "
    private static final int MARK_PREFIX_LENGTH = 5;       // "mark "
    private static final int BY_SLASH_OFFSET = 4;          // "/by "
    private static final int FROM_SLASH_OFFSET = 6;        // "/from "
    private static final int TO_SLASH_OFFSET = 4;          // "/to "

    private Scanner scanner = new Scanner(System.in);
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
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
            if (taskCount == MAX_TASKS) {
                break;
            }
        }
        System.out.println(BYE_MESSAGE);
    }

    private void printList() {
        if (taskCount == 0) {
            System.out.println("No tasks yet!");
            return;
        }
        System.out.println("Here are the tasks in your list\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ": " + tasks[i].toString());
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
            } else {
                System.out.println("I don't understand what you mean");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void handleMark(String line) throws IOException{
        if (hasNoDescription(line, "mark")) {
            return;
        }
        int taskNum = Integer.parseInt(line.substring(MARK_PREFIX_LENGTH)) - 1;
        tasks[taskNum].markAsDone();
        System.out.println("Nice I've marked this as done!\n" + tasks[taskNum].toString());
        saveTasks();
    }

    private void handleTodo(String line) throws IOException {
        if (hasNoDescription(line, "todo")) {
            return;
        }
        tasks[taskCount] = new Todo(line.substring(TODO_PREFIX_LENGTH));
        taskCount++;
        System.out.println(tasks[taskCount - 1].toString());
        saveTasks();
    }

    private void handleDeadline(String line) throws IOException {
        if (hasNoDescription(line, "deadline")) {
            return;
        }
        int slashIndex = line.indexOf("/");
        String byDate = line.substring(slashIndex + BY_SLASH_OFFSET).trim();
        String description = line.substring(DEADLINE_PREFIX_LENGTH, slashIndex).trim();
        tasks[taskCount] = new Deadline(description, byDate);
        taskCount++;
        System.out.println(tasks[taskCount - 1].toString());
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
        tasks[taskCount] = new Event(description, toWhen, fromWhen);
        taskCount++;
        System.out.println(tasks[taskCount - 1].toString());
        saveTasks();
    }

    private boolean hasNoDescription(String line, String command) {
        if (line.trim().equals(command)) {
            System.out.println("You need a description for your " + command);
            return true;
        }
        return false;
    }
    private void saveTasks() throws IOException{
        String text = "";
        for(int i = 0; i < taskCount; i++){
            if(tasks[i] instanceof Todo){
                text += "T | " + ((tasks[i].isDone ? "1" : "0") + " | " + tasks[i].description + "\n");
            } else if (tasks[i] instanceof Deadline) {
                Deadline d = (Deadline) tasks[i];
                text += "D | " + ((tasks[i].isDone ? "1" : "0") + " | " + tasks[i].description + " | " + d.getByDate() + "\n");
            } else if (tasks[i] instanceof Event){
                Event e = (Event) tasks[i];
                text += "E | " + ((tasks[i].isDone ? "1" : "0") + " | " + tasks[i].description + " | " + e.getFromWhen() + " | " + e.getToWhen() + "\n");
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
                tasks[taskCount] = new Todo(parts[2]);
            } else if (parts[0].equals("D")) {
                tasks[taskCount] = new Deadline(parts[2], parts[3]);
            } else if (parts[0].equals("E")) {
                tasks[taskCount] = new Event(parts[2], parts[3], parts[4]);
            }
            if (parts[1].equals("1")) {
                tasks[taskCount].markAsDone();
            }
            taskCount++;
        }
        fileScanner.close();
    }
}