package dioxo.migi.ListerTaches;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

public interface TachesPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(TachesEvent event);


    void chercherNotes(String tag);
    void chercherNotes();
    void afficherNotes(ArrayList<Note> notes);
    void afficherBackgroundVide();

    void closeSession();

    void chercherNotesRevision();
}
