import java.io.IOException;

/**
 * Abstract base class for all commands in the Roshy chatbot.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler
     * @param storage the storage handler
     * @throws IOException if there is an error during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if the app should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}