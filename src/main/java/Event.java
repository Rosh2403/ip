public class Event extends Task{

    private String fromWhen;
    private String toWhen;

    public String getFromWhen(){
        return fromWhen;
    }
    public String getToWhen(){
        return toWhen;
    }
    public Event(String description, String fromWhen, String toWhen){
        super(description);
        this.fromWhen = fromDate;
        this.toWhen = toDate;
    }
}
