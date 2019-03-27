package dioxo.migi.NavigationDrawer;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.Objets.Objs.User;

class NavigationEvent {
    public static final int SESSION_CLOSE_SUCCESS = 0;
    public static final int USER_SUCCESS = 1;
    public static final int USER_ERROR = 2;

    private int eventType;
    private String message;
    private User user;
    public NavigationEvent(int eventType) {
        this.eventType = eventType;
    }

    public NavigationEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public NavigationEvent(int eventType, User user) {
        this.eventType = eventType;
        this.user = user;
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

    public User getUser() {
        return user;
    }
}
