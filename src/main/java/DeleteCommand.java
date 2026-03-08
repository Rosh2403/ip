import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deletedTask = tasks.delete(index);
        ui.showDeleted(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}