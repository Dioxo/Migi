package dioxo.migi.Authentication;


import android.content.SharedPreferences;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;
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
                    view.enableInputs();
                    view.hideProgressBar();
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

    @Override
    public void checkAlreadyConnected() {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);

        if(id_user  != null) {
            Log.i("Session LOl" , "Already Connected" );
            Log.i("Session LOl" , id_user);

            if (view != null) {
                view.enableInputs();
                view.hideProgressBar();
                view.goToNextPage();
            }
        }

    }

}
