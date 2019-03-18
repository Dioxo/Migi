package dioxo.migi.listeTags;

import java.util.ArrayList;

class TagsEvent {
    public static final int SUCESS = 0;
    public static final int ERROR = 1;

    private int eventType;
    private String message;
    private ArrayList<String> tags;

    public TagsEvent(int eventType) {
        this.eventType = eventType;
    }

    public TagsEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public TagsEvent(int eventType, ArrayList<String> tags) {
        this.eventType = eventType;
        this.tags = tags;
    }

    public int getEventType() {
        return this.eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
