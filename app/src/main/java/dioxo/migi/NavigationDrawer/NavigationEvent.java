package dioxo.migi.NavigationDrawer;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

class NavigationEvent {
    public static final int NOTES_SUCCESS = 0;
    public static final int NOTES_ERROR = 1;

    private int eventType;
    private String message;
    private ArrayList<Note> notes;

    public NavigationEvent(int eventType) {
        this.eventType = eventType;
    }

    public NavigationEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public NavigationEvent(int eventType, ArrayList<Note> notes) {
        this.eventType = eventType;
        this.notes = notes;
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

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
