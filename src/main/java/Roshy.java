import java.io.IOException;
/**
 * Main class for the Roshy chatbot application.
 * Initializes the UI, storage, and task list, and runs the main command loop.
 */
public class Roshy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs a Roshy instance with the given file path for storage.
     *
     * @param filePath path to the file used for saving and loading tasks
     */
    public Roshy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the chatbot, reading and executing commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RoshyException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Roshy("data/roshy.txt").run();
    }
}