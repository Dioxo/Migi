package dioxo.migi.Note;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class NotePresenterImpl implements NotePresenter {
    EventBus eventBus;
    NoteView view;
    NoteRepository noteRepository;
    public NotePresenterImpl(NoteView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        noteRepository = new NoteRepositoryImpl();
    }

    @Override
    @Subscribe
    public void onEventMainThread(NoteEvent event) {
        switch(event.getEventType()){

        }
    }

    @Override
    public void chechIfNoteClicked(Serializable note) {
        if(note != null){

            if(view!= null){
                view.chargeNote((Note) note);
            }

        }else{

            if(view != null){
                view.chargeNoteVide();
            }

        }
    }

    @Override
    public void updateNote(Note note) {
        noteRepository.updateNote(note);
    }

    @Override
    public void cancelNote() {
        //noteRepository
    }

    @Override
    public void insertNote(Note note) {
        noteRepository.insertNote(note);
    }

    @Override
    public void deleteNote(String note) {
        noteRepository.deleteNote(note);
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }


}
