package dioxo.migi.NavigationDrawer;

import java.io.Serializable;
import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

public interface NavigationView {
    void chercherNotes();
    void afficherNotes(ArrayList<Note> notes);
    void afficherBackgroundVide();

    void chechIfNoteClicked(Serializable note);

}
