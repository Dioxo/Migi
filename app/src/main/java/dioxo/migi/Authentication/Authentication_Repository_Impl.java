package dioxo.migi.Authentication;


import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

public class Authentication_Repository_Impl implements Authentication_Repository {
    private Authentication_Event event;
    private EventBus eventBus;

    public Authentication_Repository_Impl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void confirmerMDP(String email, String mdp) {
        event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OKAY);

        eventBus.post(event);
    }

}
