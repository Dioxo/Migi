package dioxo.migi.NavigationDrawer;

import android.view.View;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;
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
                afficherNotes(event.getNotes());
                break;
            case NavigationEvent.NOTES_ERROR:
                afficherBackgroundVide();
                break;

            case NavigationEvent.SESSION_CLOSE_SUCCESS:
                goToLogin();
                break;
        }
    }

    private void goToLogin() {
        if( view != null ){

            view.goToLogin();

        }
    }

    @Override
    public void chercherNotes() {
        repository.chercherNotes();
    }

    @Override
    public void afficherNotes(ArrayList<Note> notes) {
        if(view != null){
            view.afficherNotes(notes);
        }
    }

    @Override
    public void afficherBackgroundVide() {
        if(view != null){
            view.afficherBackgroundVide();
        }
    }

    @Override
    public void closeSession() {
        repository.closeSession();
    }
}
