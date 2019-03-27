package dioxo.migi.NavigationDrawer;

import android.view.View;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.Objets.Objs.User;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class NavigationPresenterImpl implements NavigationPresenter {
    ViewNavigation view;
    NavigationRepository repository;
    EventBus eventBus;

    public NavigationPresenterImpl(ViewNavigation navigationDrawer) {
        view = navigationDrawer;
        repository = new NavigationRepositoryImpl();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Subscribe
    @Override
    public void onEventMainThread(NavigationEvent event) {
        switch (event.getEventType()){

            case NavigationEvent.SESSION_CLOSE_SUCCESS:
                goToLogin();
                break;

            case NavigationEvent.USER_ERROR:
                //Si on a eu un probleme pour charger les info, on va re chercher
                repository.chercherInformationUser();
                break;

            case NavigationEvent.USER_SUCCESS:
                changerCredentiels(event.getUser());
                break;
        }
    }

    private void changerCredentiels(User user) {
        if(view != null){
            view.changerCredentiels(user);
        }
    }

    private void goToLogin() {
        if( view != null ){
            view.goToLogin();
        }
    }



    @Override
    public void closeSession() {
        repository.closeSession();
    }

    @Override
    public void chercherInformationUser() {
        repository.chercherInformationUser();
    }
}
