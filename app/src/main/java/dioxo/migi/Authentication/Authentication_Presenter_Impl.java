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
    }

}
