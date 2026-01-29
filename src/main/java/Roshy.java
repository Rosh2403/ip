import java.util.Scanner;

public class Roshy {
    public static void main(String[] args) {
        String logo = " __  __            _       \n"
                + "|  \\/  | __ _ _ __(_) ___  \n"
                + "| |\\/| |/ _` | '__| |/ _ \\ \n"
                + "| |  | | (_| | |  | | (_) |\n"
                + "|_|  |_|\\__,_|_|  |_|\\___/ \n";
        System.out.println(logo + " Hello I'm Roshy\n What can I do for you?\n");
        Scanner in = new Scanner(System.in);

        String line = "";
        while(!line.equals("bye")) {
            line = in.nextLine();
            if(line.equals("bye")){
                break;
            }
            System.out.println(line);
            if(line.equals("Mario")){
                System.out.println("Mario? MArioooooo LUIGIIIII MARIOOOO LUGIII MARIO" + logo + logo);
            }
        }
        System.out.println("BYEEE ITS Super Smash BROS TIME");

    }
}
