import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private final int id;
    private String title;
    private String body;
    private final LocalDateTime timeCreated;

    public Entry(int id,String title,String body){
        this.id = id;
        this.title = title;
        this.body = body;
        this.timeCreated = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBody(){
        return this.body;
    }

    public LocalDateTime getTimeCreated(){
        return timeCreated;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return
                "title : " + title + '\n' +
                "body : " + body + '\n' +
                "timeCreated :" + timeCreated.format(formatter);
    }
}
