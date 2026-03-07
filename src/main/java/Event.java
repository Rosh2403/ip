public class Event extends Task {

    private String fromWhen;
    private String toWhen;

    public String getFromWhen() {
        return fromWhen;
    }

    public String getToWhen() {
        return toWhen;
    }

    public Event(String description, String toWhen, String fromWhen) {
        super(description);
        this.fromWhen = fromWhen;
        this.toWhen = toWhen;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromWhen + " to: " + toWhen + ")";
    }
}