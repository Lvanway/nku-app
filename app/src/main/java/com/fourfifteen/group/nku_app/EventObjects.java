package com.fourfifteen.group.nku_app;

import java.util.Date;
public class EventObjects {
    private int id;
    private String description;
    private Date date;
    private String time;
    private String eventType;
    public EventObjects(String description, Date date) {
        this.description = description;
        this.date = date;
    }
    public EventObjects(int id, String description, Date date, String time, String eventType) {
        this.date = date;
        this.description = description;
        this.id = id;
        this.time = time;
        this.eventType = eventType;
    }
    public int getId() {
        return id;
    }
    public String getMessage() {
        return description;
    }
    public Date getDate() {
        return date;
    }
}