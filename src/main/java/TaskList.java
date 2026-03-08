import java.util.ArrayList;

/**
 * Represents the list of tasks and provides operations to manage them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the given index.
     *
     * @param index the zero-based index of the task to delete
     * @return the task that was deleted
     */
    public Task delete(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Returns the task at the given index.
     *
     * @param index the zero-based index of the task
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}