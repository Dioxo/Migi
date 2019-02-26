package dioxo.migi.NavigationDrawer;

import android.view.View;

import org.greenrobot.eventbus.Subscribe;

import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class NavigationPresenterImpl implements NavigationPresenter {
    NavigationView view;
    NavigationRepository repository;
    EventBus eventBus;

    public NavigationPresenterImpl(NavigationView navigationDrawer) {
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
            case NavigationEvent.NOTES_SUCCESS:
                break;
            case NavigationEvent.NOTES_ERROR:
                break;
        }
    }

    @Override
    public void chercherNotes() {
        repository.chercherNotes();
    }

    @Override
    public void afficherNotes() {
        if(view != null){
            //view.afficherNotes();
        }

    }
}
