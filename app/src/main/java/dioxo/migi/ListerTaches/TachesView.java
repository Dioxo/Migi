package dioxo.migi.ListerTaches;

import java.io.Serializable;
import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

public interface TachesView {
    void chercherNotes();
    void afficherNotes(ArrayList<Note> notes);
    void afficherBackgroundVide();
}
