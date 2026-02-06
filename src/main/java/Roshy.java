import java.util.Scanner;

public class Roshy {
    public static void main(String[] args) {
        String logo = " __  __            _       \n" + "|  \\/  | __ _ _ __(_) ___  \n" + "| |\\/| |/ _` | '__| |/ _ \\ \n" + "| |  | | (_| | |  | | (_) |\n" + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
        System.out.println(logo + " Hello I'm Roshy\n What can I do for you?\n");
        Scanner in = new Scanner(System.in);
        Task[] lister = new Task[100];
        String separator = "    ____________________________________________________________";

        int index = 0;
        String line = "";
        while (!line.equals("bye")) {
            //reading the next line
            line = in.nextLine();
            //so that the value bye is not repeated
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                if (index == 0) {
                    System.out.println("No tasks yet!");
                } else {
                    System.out.println("Here are the tasks in your list\n");
                    for (int i = 0; i < index; i++) {
                        if (lister[i] instanceof Deadline) {
                            // It's a Deadline - print with date
                            Deadline d = (Deadline) lister[i];
                            System.out.println((i + 1) + ": [D][" + d.getStatusIcon() + "] " + d.description + " (by: " + d.getByDate() + ")");
                        } else if (lister[i] instanceof Todo) {
                            // It's a Todo - print with [T]
                            System.out.println((i + 1) + ": [T][" + lister[i].getStatusIcon() + "] " + lister[i].description);
                        } else {
                            // It's a regular Task
                            System.out.println((i + 1) + ": [" + lister[i].getStatusIcon() + "] " + lister[i].description);
                        }
                    }
                }
            } else if (line.equals("Mario")) {
                System.out.println(separator + "\n" + "Mario? MArioooooo LUIGIIIII MARIOOOO LUGIII MARIO" + logo + logo);
            } else if (line.startsWith("mark")) {
                int taskNum = Integer.parseInt(line.substring(5)) - 1;
                lister[taskNum].markAsDone();
                System.out.println("Nice Ive marked this as done!\n" + "[" + lister[taskNum].getStatusIcon() + "] " + lister[taskNum].description);
            } else if (line.startsWith("todo")) {
                lister[index] = new Todo(line.substring(5));
                index++;
                System.out.println("[T]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description);
            } else if (line.startsWith("deadline")) {
                int slashIndex = line.indexOf("/");
                String byDate = line.substring(slashIndex + 4).trim();
                String description = line.substring(9, slashIndex).trim();
                lister[index] = new Deadline(description, byDate);
                index++;
                System.out.println("[D]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description + " (by: " + byDate + ")");
            } else if (line.startsWith("event")) {
                int slashIndex1 = line.indexOf("/");
                int slashIndex2 = line.indexOf("/", slashIndex1 + 1);
                String fromWhen = line.substring(slashIndex1 + 6, slashIndex2).trim();
                String toWhen = line.substring(slashIndex2 + 4).trim();
                String description = line.substring(6, slashIndex1).trim();
                lister[index] = new Event(description, fromWhen, toWhen);
                index++;
                System.out.println("[E]" + "[" + lister[index - 1].getStatusIcon() + "] " + lister[index - 1].description + " (from: " + fromWhen + " to: " + toWhen + ")");
            } else {
                lister[index] = new Task(line);
                index++;
                System.out.println("added: " + line);
            }

            if (index == 100) {
                break;
            }
        }
        System.out.println("BYEEE ITS Super Smash BROS TIME");
    }
}


