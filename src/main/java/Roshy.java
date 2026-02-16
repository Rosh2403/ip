import java.util.Scanner;

public class Roshy {
    private String logo = " __  __            _       \n" + "|  \\/  | __ _ _ __(_) ___  \n" + "| |\\/| |/ _` | '__| |/ _ \\ \n" + "| |  | | (_| | |  | | (_) |\n" + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
    private Scanner in = new Scanner(System.in);
    private static final int MAX_TASKS = 100;
    private Task[] lister = new Task[MAX_TASKS];
    private String separator = "    ____________________________________________________________";
    private int index = 0;
    //static final because this number is a constant and whenever an instance of Roshy is created we dont need to waste by creating another int = 100

    public static void main(String[] args) {
        Roshy roshy = new Roshy();
        roshy.run();
    }

    public void run() {
        System.out.println(logo + " Hello I'm Roshy\n What can I do for you?\n");
        String line = "";
        while (!line.equals("bye")) {
            //reading the next line
            line = in.nextLine();
            //remove repetition
            if (line.equals("bye")) {
                break;
            }
            processCommand(line);
            if (index == MAX_TASKS) {
                break;
            }
        }
        System.out.println("BYEEE ITS Super Smash BROS TIME");
    }

    private void printList() {
        if (index == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list\n");
            for (int i = 0; i < index; i++) {
                if (lister[i] instanceof Deadline) {
                    Deadline d = (Deadline) lister[i];
                    System.out.println((i + 1) + ": [D][" + d.getStatusIcon() + "] " + d.description + " (by: " + d.getByDate() + ")");
                } else if (lister[i] instanceof Todo) {

                    System.out.println((i + 1) + ": [T][" + lister[i].getStatusIcon() + "] " + lister[i].description);
                } else {
                    // It's a regular Task
                    System.out.println((i + 1) + ": [" + lister[i].getStatusIcon() + "] " + lister[i].description);
                }
            }
        }
    }

    private void processCommand(String line) {
        if (line.equals("list")) {
            printList();
        } else if (line.equals("Mario")) {
            handleMario();
        } else if (line.startsWith("mark")) {
            handleMark(line);
        } else if (line.startsWith("todo")) {
            handleTodo(line);
        } else if (line.startsWith("deadline")) {
            handleDeadline(line);
        } else if (line.startsWith("event")) {
            handleEvent(line);
        } else {
            addTask(line);
        }

    }

    private void handleMario() {
        System.out.println(separator + "\n" + "Mario? MArioooooo LUIGIIIII MARIOOOO LUGIII MARIO" + logo + logo);
    }

    private void handleMark(String line) {
        int taskNum = Integer.parseInt(line.substring(5)) - 1;
        lister[taskNum].markAsDone();
        System.out.println("Nice Ive marked this as done!\n" + "[" + lister[taskNum].getStatusIcon() + "] " + lister[taskNum].description);
    }

    private void handleTodo(String line) {
        lister[index] = new Todo(line.substring(5));
        index++;
        System.out.println("[T]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description);
    }

    private void handleDeadline(String line) {
        int slashIndex = line.indexOf("/");
        String byDate = line.substring(slashIndex + 4).trim();
        String description = line.substring(9, slashIndex).trim();
        lister[index] = new Deadline(description, byDate);
        index++;
        System.out.println("[D]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description + " (by: " + byDate + ")");
    }

    private void handleEvent(String line) {
        int slashIndex1 = line.indexOf("/");
        int slashIndex2 = line.indexOf("/", slashIndex1 + 1);
        String fromWhen = line.substring(slashIndex1 + 6, slashIndex2).trim();
        String toWhen = line.substring(slashIndex2 + 4).trim();
        String description = line.substring(6, slashIndex1).trim();
        lister[index] = new Event(description, fromWhen, toWhen);
        index++;
        System.out.println("[E]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description + " (from: " + fromWhen + " to: " + toWhen + ")");
    }

    private void addTask(String line) {
        lister[index] = new Task(line);
        index++;
        System.out.println("added: " + line);
    }
}


