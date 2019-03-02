package dioxo.migi.Note;

import java.io.Serializable;

import dioxo.migi.Objets.Objs.Note;

public interface NotePresenter {
    void updateNote(Note note);
    void cancelNote();

    void insertNote(Note note);
    void deleteNote(String note);

    void onCreate();
    void onDestroy();
    void onEventMainThread(NoteEvent event);


    //COnfirmer si y a une note passé comme argument
    void chechIfNoteClicked(Serializable note);
}
