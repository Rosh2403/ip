import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.get(index).markAsDone();
        ui.showMarked(tasks.get(index));
        storage.save(tasks.getTasks());
    }
}