package dioxo.migi.Note;

class NoteEvent {

    public static final int UPDATE_SUCCESS = 0;
    public static final int UPDATE_ERROR = 1;


    public static final int INSERT_SUCCESS = 2;
    public static final int INSERT_ERROR = 3;


    public static final int DELETE_SUCCESS = 4;
    public static final int DELETE_ERROR = 5;

    public static final int TAG_INSERT_SUCCESS = 6;
    public static final int TAG_INSERT_ERROR = 7;

    public static final int REVISER_NOTE_SUCCESS = 8;
    public static final int REVISER_NOTE_ERROR = 9;
    public static final int DELETE_TAG_SUCCESS = 10;
    public static final int DELETE_TAG_ERROR = 11;

    private int eventType;
    private String message;

    public NoteEvent(int eventType) {
        this.eventType = eventType;
    }

    public NoteEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
