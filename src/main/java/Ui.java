import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = "_____\n" + "|  \\/  | __ _ _ __(_) ___  \n" + "| |\\/| |/ _` | '__| |/ _ \\ \n" + "| |  | | (_| | |  | | (_) |\n" + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LOGO + " Hello I'm Roshy\n What can I do for you?\n");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet!");
            return;
        }
        System.out.println("Here are the tasks in your list\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i).toString());
        }
    }

    public void showMarked(Task task) {
        System.out.println("Nice I've marked this as done!\n" + task.toString());
    }

    public void showDeleted(Task task, int remainingSize) {
        System.out.println("Noted. I have removed this task\n" + task.toString() + "\nNow you have " + remainingSize + " tasks in the list");
    }

    public void showTaskAdded(Task task) {
        System.out.println(task.toString());
    }

    public void showUnknownCommand() {
        System.out.println("I don't understand what you mean");
    }

    public void showError(String message) {
        System.out.println("An error occurred: " + message);
    }
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            System.out.println("No matching tasks found!");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ": " + matchingTasks.get(i).toString());
        }
    }
}