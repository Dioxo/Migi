package dioxo.migi.ListerTaches;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

class TachesEvent {
    public static final int NOTES_SUCCESS = 0;
    public static final int NOTES_ERROR = 1;
    public static final int SESSION_CLOSE_SUCCESS = 2;

    private int eventType;
    private String message;
    private ArrayList<Note> notes;

    public TachesEvent(int eventType) {
        this.eventType = eventType;
    }

    public TachesEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public TachesEvent(int eventType, ArrayList<Note> notes) {
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
