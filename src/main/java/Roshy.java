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
        String[] lister = new String[100];
        String separator = "    ____________________________________________________________";

        int index = 0;
        String line = "";
        while(!line.equals("bye")) {
            //reading the next line
            line = in.nextLine();
            //so that the value bye is not repeated
            if(line.equals("bye")){
                break;
            }
            if(line.equals("list")){
                if(index == 0) {
                    System.out.println("No tasks yet!");
                }
                for(int i = 0; i < index; i++){
                    System.out.println((i+1) + ": " + lister[i]);
                }
            }
            else if(line.equals("Mario")){
                System.out.println(separator +"\n" + "Mario? MArioooooo LUIGIIIII MARIOOOO LUGIII MARIO" + logo + logo);
            }
            else{
                lister[index] = line;
                index++;
                System.out.println("added: " + line);
            }

            if(index == 100){
                break;
            }
        }
        System.out.println("BYEEE ITS Super Smash BROS TIME");

    }
}
