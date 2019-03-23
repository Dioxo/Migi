package dioxo.migi.Register;

class RegisterEvent {

    public static final int REGISTER_SUCCESS = 0;
    public static final int REGISTER_ERROR = 1;


    private int eventType;
    private String message;

    public RegisterEvent(int eventType) {
        this.eventType = eventType;
    }

    public RegisterEvent(int eventType, String message) {
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
