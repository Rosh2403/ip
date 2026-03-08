import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String byDate;

    public AddDeadlineCommand(String description, String byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Deadline(description, byDate);
        tasks.add(task);
        ui.showTaskAdded(task);
        storage.save(tasks.getTasks());
    }
}