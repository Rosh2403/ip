/**
 * Represents a task with a deadline date.
 */
public class Deadline extends Task{

    private String byDate;

    public String getByDate() {
        return byDate;
    }

    public Deadline(String description, String byDate){
        super(description);
        this.byDate = byDate;
    }

}
