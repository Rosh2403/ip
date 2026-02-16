public class Event extends Task{

    private String fromWhen;
    private String toWhen;

    public String getFromWhen(){
        return fromWhen;
    }
    public String getToWhen(){
        return toWhen;
    }
    public Event(String description, String toWhen, String forWhen){
        super(description);
        this.fromWhen = forWhen;
        this.toWhen = toWhen;
    }
}
