package dioxo.migi.ListerTaches;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class TachesPresenterImpl implements TachesPresenter {
    TachesView view;
    TachesRepository repository;
    EventBus eventBus;

    public TachesPresenterImpl(TachesView tachesView) {
        view = tachesView;
        repository = new TachesRepositoryImpl();
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
    public void onEventMainThread(TachesEvent event) {
        switch (event.getEventType()){
            case TachesEvent.NOTES_SUCCESS:
                afficherNotes(event.getNotes());
                break;
            case TachesEvent.NOTES_ERROR:
                afficherBackgroundVide();
                break;

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
