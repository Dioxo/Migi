package dioxo.migi.Authentication;


public class Authentication_Event {
    public static final int AUTHENTICATION_OKAY = 0;

    public static final  int AUTHENTICATION_ERROR = 1;

    private int eventType;
    private String message;

    public Authentication_Event(int eventType) {
        this.eventType = eventType;
    }


    public void setEventType(int eventType) {
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
