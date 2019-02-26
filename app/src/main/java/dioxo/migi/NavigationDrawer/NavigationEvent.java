package dioxo.migi.NavigationDrawer;

class NavigationEvent {
    public static final int NOTES_SUCCESS = 0;
    public static final int NOTES_ERROR = 1;

    private int eventType;
    private String message;

    public NavigationEvent(int eventType) {
        this.eventType = eventType;
    }

    public NavigationEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
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

}
