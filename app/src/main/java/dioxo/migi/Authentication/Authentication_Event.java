package dioxo.migi.Authentication;


public class Authentication_Event {
    public static final int AUTHENTICATION_OKAY = 0;

    public static final  int AUTHENTICATION_ERROR = 1;

    private int eventType;
    private String message;

    public Authentication_Event(int eventType) {
        this.eventType = eventType;
    }

    public Authentication_Event(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }


    public void setEventType(int eventType) {
        this.eventType = eventType;
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

    @Override
    public String toString() {
        return "Authentication_Event{" +
                "eventType=" + eventType +
                ", message='" + message + '\'' +
                '}';
    }
}
