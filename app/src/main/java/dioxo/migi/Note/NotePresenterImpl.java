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

                case NoteEvent.TAG_INSERT_ERROR:
                    view.insert(false);
                    break;

                case NoteEvent.TAG_INSERT_SUCCESS:
                    view.insert(true);
                    view.ajouterTagAuView(event.getMessage());
                    break;

                case NoteEvent.REVISER_NOTE_SUCCESS:
                    view.reviserNote(true);
                    break;


                case NoteEvent.REVISER_NOTE_ERROR:
                    view.reviserNote(false);
                    break;

                case NoteEvent.DELETE_TAG_ERROR:
                    view.reviserNote(false);
                    break;
                case NoteEvent.DELETE_TAG_SUCCESS:
                    view.reviserNote(true);
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
    public void ajouterTag(String tagNom) {
        noteRepository.ajouterTag(tagNom);
    }

    @Override
    public void reviserNote(String qualification) {
        noteRepository.reviserNote(qualification);
    }

    @Override
    public void effacerTag(String textTag) {
        noteRepository.effacerTag(textTag);
    }

    @Override
    public void updateNote(Note note) {
        showProgressBar();
        disableInputs();
        noteRepository.updateNote(note);
    }

    private void showProgressBar() {
        if(view != null){
            view.showProgressBar();
        }
    }

    @Override
    public void cancelNote() {
        //noteRepository
    }

    @Override
    public void insertNote(Note note) {
        showProgressBar();
        disableInputs();
        noteRepository.insertNote(note);
    }

    private void disableInputs() {
        if(view != null){
            view.disableInputs();
        }
    }

    @Override
    public void deleteNote() {
        showProgressBar();
        disableInputs();
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
