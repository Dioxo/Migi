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
        if(view != null){


            switch(event.getEventType()){
                case NoteEvent.UPDATE_SUCCESS:
                    view.update(true);
                    break;

                case NoteEvent.UPDATE_ERROR:
                    view.update(false);
                    break;


                case NoteEvent.DELETE_SUCCESS:
                    view.delete(true);
                    break;


                case NoteEvent.DELETE_ERROR:
                    view.delete(false);
                    break;


                case NoteEvent.INSERT_SUCCESS:
                    view.insert(true);
                    break;


                case NoteEvent.INSERT_ERROR:
                    view.insert(false);
                    break;
            }

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
    public void deleteNote() {
        noteRepository.deleteNote();
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
