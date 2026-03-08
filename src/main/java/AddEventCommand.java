import java.io.IOException;

public class AddEventCommand extends Command {
    private String description;
    private String fromWhen;
    private String toWhen;

    public AddEventCommand(String description, String fromWhen, String toWhen) {
        this.description = description;
        this.fromWhen = fromWhen;
        this.toWhen = toWhen;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Event(description, toWhen, fromWhen);
        tasks.add(task);
        ui.showTaskAdded(task);
        storage.save(tasks.getTasks());
    }
}