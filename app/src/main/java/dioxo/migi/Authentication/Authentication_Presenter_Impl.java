package dioxo.migi.Authentication;


import org.greenrobot.eventbus.Subscribe;

import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

public class Authentication_Presenter_Impl implements Authentication_Presenter {
    private Authentication_View view;
    private EventBus eventBus;
    private Authentication_Repository repository;

    public Authentication_Presenter_Impl(Authentication_View view) {
        this.view = view;
        repository = new Authentication_Repository_Impl();
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void confirmerMDP(String email, String mdp) {
        if(view != null){
            view.showProgressBar();
            view.disableInputs();
        }

        repository.confirmerMDP(email,mdp);
    }

    public void goToNextPage() {
        view.goToNextPage();
    }

    public void onCreate() {
        eventBus.register(this);
    }

    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(Authentication_Event authenticationEvent) {
        switch (authenticationEvent.getEventType()){

            case Authentication_Event.AUTHENTICATION_OKAY:
                if(view != null) {
                    view.goToNextPage();
                }
                break;

            case Authentication_Event.AUTHENTICATION_ERROR:
                if(view != null){
                    view.loginError(authenticationEvent.getMessage());
                    view.enableInputs();
                    view.hideProgressBar();
                }
                break;
        }
    }

}
